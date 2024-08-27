package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    private final Logger logger = LoggerFactory.getLogger(ExceptionHandlerAdvice.class);

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<Object> handleException(Throwable t) {
        logger.warn("Error in application", t);
        return ResponseEntity
                .status(HttpStatus.I_AM_A_TEAPOT)
                .body(String.format("Your code died with with the error: '%s'. Please try again. :)", t.getMessage()));
    }
}
