package com.projects.itau_challenge.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projects.itau_challenge.domain.transaction.Transaction;
import com.projects.itau_challenge.repositories.TransactionRepository;

@Service
public class TransactionService {
    
    @Autowired
    TransactionRepository repository;

    public Transaction createTransaction(Transaction t) {
        return repository.save(t);
    }
}