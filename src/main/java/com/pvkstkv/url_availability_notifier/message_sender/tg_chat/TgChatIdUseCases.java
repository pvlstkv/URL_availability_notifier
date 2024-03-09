package com.pvkstkv.url_availability_notifier.message_sender.tg_chat;

import java.util.List;

public interface TgChatIdUseCases {
    TgChatId saveSubscriber(String chatId);
    void deleteSubscriber(String chatId);
    List<TgChatId> getAllSubscribers();
}
