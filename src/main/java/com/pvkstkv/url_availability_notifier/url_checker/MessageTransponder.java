package com.pvkstkv.url_availability_notifier.url_checker;

import com.pvkstkv.url_availability_notifier.url_checker.messages.Message;

import java.util.concurrent.BlockingQueue;

public class MessageTransponder implements Runnable {

    private final BlockingQueue<Message> messages;

    public MessageTransponder(BlockingQueue<Message> messages) {
        this.messages = messages;
    }

    @Override
    public void run() {
        while (true) {
            if ((messages.peek()) != null) {
                Message message = null;
                try {
                    message = messages.take();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(message);
            }
        }
    }
}
