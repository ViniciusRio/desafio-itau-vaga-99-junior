package com.itau.desafio;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    Logger logger = LogManager.getLogger(TransactionService.class);

    @ExceptionHandler(InvalidTransactionException.class)
    public final ResponseEntity<ErrorResponse> handleInvalidTransactionException(
            InvalidTransactionException invalidTransactionException, WebRequest request) {
        logger.error("Erro de transação inválida: {}", invalidTransactionException.getMessage());

        ErrorResponse errorResponse = new ErrorResponse(
                invalidTransactionException.getMessage(),
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "TRANSACAO_INVALIDA",
                LocalDateTime.now().toString()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleInvalidTransaction(IllegalArgumentException ex) {
        logger.error("Argumento inválido: {}", ex.getMessage());

        ErrorResponse errorResponse = new ErrorResponse(
                "Argumento inválido: " + ex.getMessage(),
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "ARGUMENTO_INVALIDO",
                LocalDateTime.now().toString()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        logger.error("Erro não tratado: {}", ex.getMessage());

        ErrorResponse errorResponse = new ErrorResponse(
                "Ocorreu um erro inesperado: " + ex.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "ERRO_INTERNO",
                LocalDateTime.now().toString()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
