package com.khaydev.microservices.currencyexchangeservice;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus()
public class OperationFailedException extends RuntimeException{

    public OperationFailedException() {
    }

    public OperationFailedException(String message) {
        super(message);
    }
}
