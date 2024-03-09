package com.pvkstkv.url_availability_notifier.url_checker.messages;

import com.pvkstkv.url_availability_notifier.rule_api.model.Rule;

public class MismatchMessage extends Message{
    public MismatchMessage(Rule rule, Integer receivedStatusCode) {
        super(rule, receivedStatusCode);
    }
}
