package com.pvkstkv.url_availability_notifier.url_checker.messages;

import com.pvkstkv.url_availability_notifier.rule_api.model.Rule;
import com.pvkstkv.url_availability_notifier.url_checker.State;

public class Message {
    protected Rule rule;

    protected State state;
    protected Integer receivedStatusCode;

    public Message(Rule rule, State state, Integer receivedStatusCode) {
        this.rule = rule;
        this.state = state;
        this.receivedStatusCode = receivedStatusCode;
    }

    public Message(Rule rule, State state) {
        this.rule = rule;
        this.state = state;
    }

    public Message(Rule rule, Integer receivedStatusCode) {
        this.rule = rule;
        this.receivedStatusCode = receivedStatusCode;
    }

    @Override
    public String toString() {
        return "Message{" +
                "rule=" + rule +
                ", state=" + state +
                ", receivedStatusCode=" + receivedStatusCode +
                '}';
    }
}
