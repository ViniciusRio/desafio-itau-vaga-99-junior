package com.itau.desafio;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    Logger logger = LogManager.getLogger(TransactionService.class);

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Void> handleInvalidTransaction() {
        logger.error("Invalid transaction");
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Void> handleGenericException(Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

}
