package com.pvkstkv.url_availability_notifier.layers.domain.exception;

public class NotValidStatusCodeException extends Throwable {

    public NotValidStatusCodeException() {
    }

    public NotValidStatusCodeException(String message) {
        super(message);
    }

    public NotValidStatusCodeException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotValidStatusCodeException(Throwable cause) {
        super(cause);
    }

    public NotValidStatusCodeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
