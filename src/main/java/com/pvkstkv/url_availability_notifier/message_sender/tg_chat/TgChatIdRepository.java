package com.pvkstkv.url_availability_notifier.message_sender.tg_chat;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TgChatIdRepository extends JpaRepository<TgChatId, String> {
}
