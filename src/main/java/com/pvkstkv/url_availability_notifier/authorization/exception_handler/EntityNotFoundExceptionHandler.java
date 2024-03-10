package com.pvkstkv.url_availability_notifier.authorization.exception_handler;

import com.pvkstkv.url_availability_notifier.authorization.Message;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
public class EntityNotFoundExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Message methodArgumentNotValidException(EntityNotFoundException ex) {
        return new Message(ex.getMessage());
    }
}
