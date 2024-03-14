package com.pvkstkv.url_availability_notifier.layers.domain.exception;

public class NotValidPeriodCheckException extends Throwable {
    public NotValidPeriodCheckException() {
    }

    public NotValidPeriodCheckException(String message) {
        super(message);
    }

    public NotValidPeriodCheckException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotValidPeriodCheckException(Throwable cause) {
        super(cause);
    }

    public NotValidPeriodCheckException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
