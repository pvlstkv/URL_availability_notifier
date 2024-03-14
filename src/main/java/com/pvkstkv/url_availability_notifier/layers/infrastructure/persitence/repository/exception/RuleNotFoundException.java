package com.pvkstkv.url_availability_notifier.layers.infrastructure.persitence.repository.exception;

public class RuleNotFoundException extends Throwable {
    public RuleNotFoundException() {
    }

    public RuleNotFoundException(String message) {
        super(message);
    }

    public RuleNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public RuleNotFoundException(Throwable cause) {
        super(cause);
    }

    public RuleNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
