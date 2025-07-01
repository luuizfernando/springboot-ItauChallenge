package com.projects.itau_challenge.integration;

import java.time.Instant;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projects.itau_challenge.domain.transaction.Transaction;
import com.projects.itau_challenge.repositories.TransactionRepository;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class TransactionServiceTest {
    
    @Autowired
    MockMvc mockMvc;

    @Autowired
    TransactionRepository repository;

    @Autowired
    private ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        Transaction transaction = new Transaction(100.0, Instant.now());
        repository.save(transaction);
    }

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Test
    @DisplayName("Create a transaction")
    void createTransaction() throws Exception {

        Transaction transaction = new Transaction(100.00, Instant.now());
        String transactionJson = mapper.writeValueAsString(transaction);

        mockMvc.perform(MockMvcRequestBuilders.post("/transacao")
        .contentType(MediaType.APPLICATION_JSON)
        .content(transactionJson))
        .andExpect(MockMvcResultMatchers.status().isCreated())
        .andDo(MockMvcResultHandlers.print());

    }

}