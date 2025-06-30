package com.projects.itau_challenge.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projects.itau_challenge.domain.transaction.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
   
}