package com.pvkstkv.url_availability_notifier.rule_api.service.excpetion;

public class RuleYetExistsException extends Exception{
    public RuleYetExistsException() {
    }

    public RuleYetExistsException(String message) {
        super(message);
    }

    public RuleYetExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public RuleYetExistsException(Throwable cause) {
        super(cause);
    }

    public RuleYetExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
