package com.pvkstkv.url_availability_notifier.url_checker;

import com.pvkstkv.url_availability_notifier.rule_api.model.Rule;
import com.pvkstkv.url_availability_notifier.url_checker.messages.MatchMessage;
import com.pvkstkv.url_availability_notifier.url_checker.messages.Message;
import com.pvkstkv.url_availability_notifier.url_checker.messages.MismatchMessage;
import com.pvkstkv.url_availability_notifier.url_checker.messages.UnacceptableMessage;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;

public class URLExecutor implements Runnable {

    private final Rule rule;
    private final BlockingQueue<Message> queue;

    public URLExecutor(Rule rule, BlockingQueue<Message> queue) {
        this.rule = rule;
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            URL url = new URL(rule.getUrl());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            int responseCode = connection.getResponseCode();
            Message message;
            if (responseCode != rule.getExpectedStatusCode()) {
//                message = new MatchMessage(rule, responseCode);
                message = new Message(rule, State.MATCH, responseCode);
            } else {
//                message = new MatchMessage(rule, responseCode);
                message = new Message(rule, State.MISMATCH, responseCode);
            }
            queue.add(message);
        } catch (IOException e) {
            queue.add(new Message(rule, State.UNACCEPTABLE));
        }
    }
}
