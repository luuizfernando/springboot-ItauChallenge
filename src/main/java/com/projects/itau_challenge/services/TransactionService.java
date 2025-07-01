package com.projects.itau_challenge.services;

import java.time.Instant;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projects.itau_challenge.domain.transaction.Transaction;
import com.projects.itau_challenge.domain.transaction.TransactionStatisticsDTO;
import com.projects.itau_challenge.repositories.TransactionRepository;

@Service
public class TransactionService {
    
    @Autowired
    TransactionRepository repository;

    public List<Transaction> findAll() {
        return repository.findAll();
    }

    public Transaction createTransaction(Transaction t) {
        validateTransactionValue(t);
        validateTransactionDate(t);
        return repository.save(t);
    }

    public void deleteAllTransactions() {
        repository.deleteAll();
    }

    public TransactionStatisticsDTO getTransactionStatistics(List<Transaction> transactions) {
        Instant last60Seconds = Instant.now().minusSeconds(60);
        List<Transaction> transactionsInLast60Seconds = transactions.stream()
            .filter(t -> t.getDataHora().isAfter(last60Seconds))
            .collect(Collectors.toList());

        if (transactionsInLast60Seconds.isEmpty()) {
            return new TransactionStatisticsDTO(0L, 0.0, 0.0, 0.0, 0.0);
        } else {
            DoubleSummaryStatistics stats = transactionsInLast60Seconds.stream()
                .mapToDouble(Transaction::getValor)
                .summaryStatistics();

            return new TransactionStatisticsDTO(
                stats.getCount(),
                stats.getSum(),
                stats.getAverage(),
                stats.getMin(),
                stats.getMax()
            );
        }
    }

    private void validateTransactionValue(Transaction t) {
        if (t.getValor() < 0) {
            throw new IllegalArgumentException("Transaction value cannot be negative.");
        }
    }

    private void validateTransactionDate(Transaction t) {
        Instant now = Instant.now();
        if (t.getDataHora().isAfter(now)) {
            throw new IllegalArgumentException("Transaction date cannot be in the future.");
        }
    }

}