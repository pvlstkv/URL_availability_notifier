package com.pvkstkv.url_availability_notifier.url_checker;

import com.pvkstkv.url_availability_notifier.message_sender.Retransmittable;
import com.pvkstkv.url_availability_notifier.url_checker.messages.Message;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingQueue;
import java.util.logging.LogManager;
import java.util.logging.Logger;
@Slf4j
public class MessageTransponder implements Runnable {
    private static final Logger LOGGER = Logger.getLogger(MessageTransponder.class.getName());
    private final BlockingQueue<Message> messages;
    private final Retransmittable retransmitter;

    public MessageTransponder(BlockingQueue<Message> messages, Retransmittable retransmitter) {
        this.messages = messages;
        this.retransmitter = retransmitter;
        log.info(retransmitter.toString());
    }

    @Override
    public void run() {
        LOGGER.info("start message transponder");
        while (true) {
            if ((messages.peek()) != null) {
                Message message = null;
                try {
                    message = messages.take();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                LOGGER.info("message transponder sent a message");
                LOGGER.info(message.toString());
                retransmitter.retransmit(message.toString());
            }
        }
    }
}
