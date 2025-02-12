package com.itau.desafio;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    private final List<Transaction> transactionList = new ArrayList<>();

    @PostMapping
    public ResponseEntity<Void> transactions(@RequestBody Transaction transaction) {
        // TODO Mover lógica de validação
        if (transaction.getDataHora().isAfter(OffsetDateTime.now())) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }

        if (transaction.getValor().compareTo(BigDecimal.ZERO) < 0) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }
        // TODO Ter um melhor logging
        System.out.printf("Transaction: %s%n", transaction);
        transactionList.add(transaction);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<Transaction>> getTransactions() {
        return ResponseEntity.ok(transactionList);
    }
    @DeleteMapping
    public ResponseEntity<Void> deleteTransactions() {
        transactionList.clear();
        System.out.println("Transactions deleted");
        return ResponseEntity.ok().build();

    }
    // TODO Mover exceções
    @ExceptionHandler({org.springframework.http.converter.HttpMessageNotReadableException.class})
    public ResponseEntity<Void> handleInvalidJson() {
        return ResponseEntity.badRequest().build();
    }
}
