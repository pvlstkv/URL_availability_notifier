package com.pvkstkv.url_availability_notifier.layers.domain.exception;

public class NotValidUrlException extends Throwable {
    public NotValidUrlException() {
    }

    public NotValidUrlException(String message) {
        super(message);
    }

    public NotValidUrlException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotValidUrlException(Throwable cause) {
        super(cause);
    }

    public NotValidUrlException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
