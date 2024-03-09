package com.pvkstkv.url_availability_notifier.message_sender;

import com.pvkstkv.url_availability_notifier.message_sender.tg_chat.TgChatId;
import com.pvkstkv.url_availability_notifier.message_sender.tg_chat.TgChatIdUseCases;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.Collectors;

@Slf4j
@Component
public class URLNotifierTelegramBot extends TelegramLongPollingBot implements Retransmittable {

    private final Set<String> tgSubscriberIds = new CopyOnWriteArraySet<>();
    private static final String START = "/start";
    private static final String SUBSCRIBE = "/subscribe";
    private static final String UNSUBSCRIBE = "/unsubscribe";
    private static final String HELP = "/help";
    private final TgChatIdUseCases tgChatIdUseCases;

    public URLNotifierTelegramBot(@Value("${bot.token}") String botToken, TgChatIdUseCases tgUserUseCases) {
        super(botToken);
        this.tgChatIdUseCases = tgUserUseCases;
        var allTgChats = tgUserUseCases.getAllSubscribers().stream().map(TgChatId::getId).toList();
        this.tgSubscriberIds.addAll(allTgChats);
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (!update.hasMessage() || !update.getMessage().hasText()) {
            return;
        }
        String chatId = String.valueOf(update.getMessage().getChatId());
        String message = update.getMessage().getText();
        switch (message) {
            case START -> startCommand(chatId, update);
            case HELP -> helpCommand(chatId);
            case SUBSCRIBE -> subscribeCommand(chatId);
            case UNSUBSCRIBE -> unsubscribeCommand(chatId);
            default -> unknownCommand(chatId);
        }
    }

    private void subscribeCommand(String chatId) {
        log.info(String.format("user chat id is %s", chatId));
        tgSubscriberIds.add(chatId);
        tgChatIdUseCases.saveSubscriber(chatId);
        sendMessagePerUser(chatId, "Вы подписаны на URL проверки");
    }

    private void unsubscribeCommand(String chatId) {
        tgSubscriberIds.remove(chatId);
        tgChatIdUseCases.deleteSubscriber(chatId);
        sendMessagePerUser(chatId, "Вы отписаны от URL проверок");
    }

    private void startCommand(String chatId, Update update) {
        String userName = update.getMessage().getChat().getUserName();
        var text = String.format("""
                Добро пожаловать в бот, %s!

                Для подписки/отписки от оповещений воспользуйтесь командами:
                                 %s - подписка
                                 %s - отписка
                              

                Дополнительные команды:
                /help - получение справки
                """, userName, SUBSCRIBE, UNSUBSCRIBE);
        sendMessagePerUser(chatId, text);
    }

    private void helpCommand(String chatId) {
        var text = String.format("""
                Справочная информация по боту

                Для подписки/отписки от оповещений воспользуйтесь командами:
                 %s - подписка
                 %s - отписка
                """, SUBSCRIBE, UNSUBSCRIBE);
        sendMessagePerUser(chatId, text);
    }

    private void unknownCommand(String chatId) {
        var text = "Не удалось распознать команду!";
        sendMessagePerUser(chatId, text);
    }

    @Override
    public String getBotUsername() {
        return "url_notifier";
    }

    private void sendMessagePerUser(String chatId, String messageText) {
        log.info(String.format("user chat id = %s: %s", chatId, messageText));
        SendMessage message = new SendMessage(String.valueOf(chatId), messageText);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void retransmit(String text) {
        tgSubscriberIds.forEach(subscriber -> sendMessagePerUser(subscriber, text));
    }

    @Override
    public String toString() {
        return "MyTelegramBot{" +
                "subscribers=" + tgSubscriberIds +
                '}';
    }
}

