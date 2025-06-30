package com.projects.itau_challenge.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projects.itau_challenge.domain.transaction.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
   
}