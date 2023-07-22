package com.khaydev.microservices.currencyexchangeservice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({OperationFailedException.class})
    public ResponseEntity<Object> handleOperationFailedException(OperationFailedException exception){
        Map<String, Object> response = new HashMap<>();
        response.put("message", exception.getMessage());
        return ResponseEntity.internalServerError().body(response);
    }
}
