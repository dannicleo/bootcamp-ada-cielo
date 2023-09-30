package com.dannicleo.cielo.desafio1.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DocumentExistentException extends RuntimeException{
    public DocumentExistentException(String message) {
        super(message);
    }
}
