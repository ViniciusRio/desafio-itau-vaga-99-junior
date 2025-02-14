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
                "Transação Inválida",
                LocalDateTime.now().toString()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);

    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Void> handleInvalidTransaction() {
        logger.error("Invalid transaction");
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Void> handleGenericException(Exception e) {
        // TODO: Textos devem ser armazenados em outro local para tradução
        logger.error("Erro não tratado: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

}
