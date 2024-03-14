package com.pvkstkv.url_availability_notifier.layers.domain.exception;

public class NotValidRuleValueException extends Throwable {
    public NotValidRuleValueException() {
    }

    public NotValidRuleValueException(String message) {
        super(message);
    }

    public NotValidRuleValueException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotValidRuleValueException(Throwable cause) {
        super(cause);
    }

    public NotValidRuleValueException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
