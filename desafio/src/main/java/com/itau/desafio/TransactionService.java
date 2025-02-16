package com.itau.desafio;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {
    @Value("${STATISTICS_WINDOW:60}")
    private int statisticsWindow;

    private final List<Transaction> transactionList = new ArrayList<>();
    Logger logger = LogManager.getLogger(TransactionService.class);

    public void addTransaction(Transaction transaction) {
        validTransaction(transaction);
        logger.info("Transaction: {}", transaction);
        transactionList.add(transaction);

    }

    private void validTransaction(Transaction transaction) {
        // TODO: mover para responsabilidade
        if (transaction.dataHora().isAfter(OffsetDateTime.now())) {
            throw new InvalidTransactionException("Data não pode estar no futuro");

        }
        if (transaction.valor() < 0) {
            throw new InvalidTransactionException("O valor da transação não pode ser negativo");

        }
    }

    public List<Transaction> getTransactions() {
        return new ArrayList<>(transactionList);
    }

    public void deleteTransactions() {
        logger.info("Transactions deleted");
        transactionList.clear();
    }

    public Statistic getStatistics() {
        List<Double> values = transactionList.stream()
                .filter(transaction -> transaction
                        .dataHora()
                        .isAfter(OffsetDateTime.now().minusSeconds(statisticsWindow))
                )
                .map(Transaction::valor)
                .toList();

        DoubleSummaryStatistics doubleSummaryStatistics = values.stream()
                .collect(Collectors.summarizingDouble(e -> e));
        return new Statistic(
                doubleSummaryStatistics.getCount(),
                doubleSummaryStatistics.getSum(),
                doubleSummaryStatistics.getAverage(),
                doubleSummaryStatistics.getMin(),
                doubleSummaryStatistics.getMax()

        );
    }
}
