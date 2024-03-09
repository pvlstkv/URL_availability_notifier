package com.pvkstkv.url_availability_notifier.message_sender.tg_chat;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TgUserUseCasesImpl implements TgChatIdUseCases {

    private final TgChatIdRepository repository;

    @Override
    public TgChatId saveSubscriber(String chatId) {
        TgChatId tgChatId = new TgChatId(chatId);
        return repository.save(tgChatId);
    }

    @Override
    public void deleteSubscriber(String chatId) {
        Optional<TgChatId> tgChatId = repository.findById(chatId);
        if (tgChatId.isEmpty()) return;
        repository.delete(tgChatId.get());
    }

    @Override
    public List<TgChatId> getAllSubscribers() {
        return repository.findAll();
    }
}
