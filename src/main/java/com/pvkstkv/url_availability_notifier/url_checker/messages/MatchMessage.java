package com.pvkstkv.url_availability_notifier.url_checker.messages;

import com.pvkstkv.url_availability_notifier.rule_api.model.Rule;

public class MatchMessage extends Message {

    public MatchMessage(Rule rule, Integer receivedStatusCode) {
        super(rule, receivedStatusCode);
    }
}
