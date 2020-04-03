package com.likefood.exception;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class LikefoodExceptionAdvice {
    @ExceptionHandler(LikefoodException.class)
    public ResponseEntity<String> handleInvalidInput(LikefoodException ex){

        return  ResponseEntity
                .badRequest()
                .body("{\"message\": \"" + ex.getMessage() + "\"}");
    }

}