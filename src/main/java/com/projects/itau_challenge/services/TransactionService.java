package com.projects.itau_challenge.services;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projects.itau_challenge.domain.transaction.Transaction;
import com.projects.itau_challenge.repositories.TransactionRepository;

@Service
public class TransactionService {
    
    @Autowired
    TransactionRepository repository;

    public Transaction createTransaction(Transaction t) {
        validateTransactionValue(t);
        validateTransactionDate(t);
        return repository.save(t);
    }

    public void deleteAllTransactions() {
        repository.deleteAll();
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