package com.pvkstkv.url_availability_notifier.message_sender;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Component
public class MyTelegramBot extends TelegramLongPollingBot implements Retransmittable {

    private final Set<Long> subscribers = new HashSet<>();

    private static final String START = "/start";
    private static final String SUBSCRIBE = "/subscribe";
    private static final String UNSUBSCRIBE = "/unsubscribe";
    private static final String HELP = "/help";

    public MyTelegramBot(@Value("${bot.token}") String botToken) {
        super(botToken);
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (!update.hasMessage() || !update.getMessage().hasText()) {
            return;
        }
        Long chatId = update.getMessage().getChatId();
        String message = update.getMessage().getText();
        switch (message) {
            case START -> {
                String userName = update.getMessage().getChat().getUserName();
                startCommand(chatId, userName);
            }
            case HELP -> helpCommand(chatId);
            case SUBSCRIBE -> {
                subscribers.add(chatId);
                sendMessagePerUser(chatId, "Вы подписаны на URL проверки");
            }
            case UNSUBSCRIBE -> {
                subscribers.remove(chatId);
                sendMessagePerUser(chatId, "Вы отписаны от URL проверок");
            }
            default -> unknownCommand(chatId);
        }
    }

    private void startCommand(Long chatId, String userName) {
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

    private void helpCommand(Long chatId) {
        var text = String.format("""
                Справочная информация по боту

                Для подписки/отписки от оповещений воспользуйтесь командами:
                 %s - подписка
                 %s - отписка
                """, SUBSCRIBE, UNSUBSCRIBE);
        sendMessagePerUser(chatId, text);
    }

    private void unknownCommand(Long chatId) {
        var text = "Не удалось распознать команду!";
        sendMessagePerUser(chatId, text);
    }

    @Override
    public String getBotUsername() {
        // Return your bot's username
        return "url_notifier";
    }

    private void sendMessagePerUser(Long chatId, String messageText) {
        log.info(String.format("user with id = %d: %s", chatId, messageText));
        SendMessage message = new SendMessage(String.valueOf(chatId), messageText);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void retransmit(String text) {
        subscribers.forEach(subscriber -> sendMessagePerUser(subscriber, text));
    }

    @Override
    public String toString() {
        return "MyTelegramBot{" +
                "subscribers=" + subscribers +
                '}';
    }
}

