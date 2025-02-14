package com.itau.desafio;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    public final TransactionService transactionService;
    private Logger logger = LogManager.getLogger(TransactionController.class);

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<String> transactions(@RequestBody Transaction transaction) {
        transactionService.addTransaction(transaction);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<Transaction>> getTransactions() {
        return ResponseEntity.ok(transactionService.getTransactions());
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteTransactions() {
        transactionService.deleteTransactions();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/statistics")
    public ResponseEntity<Statistic> getStatistics() {
        long startTime = System.currentTimeMillis();
        Statistic statistic = transactionService.getStatistics();
        long endTime = System.currentTimeMillis();
        logger.info("Time to calculate statistics: {}ms", endTime - startTime);
        return ResponseEntity.ok(statistic);
    }
}
