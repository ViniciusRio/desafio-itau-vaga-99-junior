package com.itau.desafio;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
@Tag(name = "Transações", description = "Gerenciamento de transações financeiras")
public class TransactionController {
    public final TransactionService transactionService;
    private Logger logger = LogManager.getLogger(TransactionController.class);

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Operation(summary = "Criar uma nova transação", description = "Adiciona uma transação ao sistema")
    @ApiResponse(responseCode = "201", description = "Transação criada com sucesso")
    @PostMapping
    public ResponseEntity<String> transactions(@RequestBody Transaction transaction) {
        transactionService.addTransaction(transaction);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Obter todas as transações", description = "Retorna a lista de todas as transações registradas")
    @ApiResponse(responseCode = "200", description = "Lista de transações retornada com sucesso",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Transaction.class)))
    @GetMapping
    public ResponseEntity<List<Transaction>> getTransactions() {
        return ResponseEntity.ok(transactionService.getTransactions());
    }

    @Operation(summary = "Deletar todas as transações", description = "Remove todas as transações armazenadas")
    @ApiResponse(responseCode = "200", description = "Todas as transações foram deletadas")
    @DeleteMapping
    public ResponseEntity<Void> deleteTransactions() {
        transactionService.deleteTransactions();
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "Obter estatísticas das transações",
            description = "Calcula e retorna estatísticas sobre as transações registradas"
    )
    @ApiResponse(responseCode = "200", description = "Estatísticas calculadas com sucesso",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Statistic.class)))
    @GetMapping("/statistics")
    public ResponseEntity<Statistic> getStatistics() {
        long startTime = System.currentTimeMillis();
        Statistic statistic = transactionService.getStatistics();
        long endTime = System.currentTimeMillis();
        logger.info("Time to calculate statistics: {}ms", endTime - startTime);
        return ResponseEntity.ok(statistic);
    }
}
