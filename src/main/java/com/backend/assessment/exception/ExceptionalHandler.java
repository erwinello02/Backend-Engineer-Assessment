package com.backend.assessment.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ExceptionalHandler {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorDetails> illegalArgumentException(IllegalArgumentException ex) {
        logger.error("Caught IllegalArgumentException", ex);
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
}