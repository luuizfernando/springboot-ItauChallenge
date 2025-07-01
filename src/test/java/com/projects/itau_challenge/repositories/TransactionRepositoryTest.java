package com.projects.itau_challenge.repositories;

import java.time.Instant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.projects.itau_challenge.domain.transaction.Transaction;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class TransactionRepositoryTest {

    @Autowired
    private TransactionRepository repository;

    @Test
    @DisplayName("The fields valor and dataHora must be not null")
    void makeTransactionCase1() {
        Transaction transaction = new Transaction(100.00, Instant.now());
        Transaction savedTransaction = repository.save(transaction);

        assertThat(savedTransaction.getValor()).isNotNull();
        assertThat(savedTransaction.getDataHora()).isNotNull();
    }

}