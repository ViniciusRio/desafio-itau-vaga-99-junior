package com.itau.desafio;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {
    private final List<Transaction> transactionList = new ArrayList<>();

    public void addTransaction(Transaction transaction) {
        validTransaction(transaction);
        transactionList.add(transaction);

    }

    private void validTransaction(Transaction transaction) {
        // TODO: mover para responsabilidade
        if (transaction.dataHora().isAfter(OffsetDateTime.now())) {
            throw new IllegalArgumentException();

        }
        if (transaction.valor() < 0) {
            throw new IllegalArgumentException();

        }
    }

    public List<Transaction> getTransactions() {
        return new ArrayList<>(transactionList);
    }

    public void deleteTransactions() {
        transactionList.clear();
    }

    public Statistic getStatistics() {
        List<Double> values = transactionList.stream()
                .filter(transaction -> transaction
                        .dataHora()
                        .isAfter(OffsetDateTime.now().minusSeconds(60))
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
