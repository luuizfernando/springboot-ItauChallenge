package com.projects.itau_challenge.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import java.time.Instant;
import static org.mockito.Mockito.never;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.projects.itau_challenge.domain.transaction.Transaction;
import com.projects.itau_challenge.services.TransactionService;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {
    
    @Mock
    private TransactionRepository repository;

    @InjectMocks
    private TransactionService service;

    @Test
    @DisplayName("Should create transaction when valor and dataHora are not null")
    public void createTransactionCase1() {
        Double valor = 100.0;
        Instant dataHora = Instant.now();
        Transaction transaction = new Transaction(valor, dataHora);
        Transaction savedTransaction = new Transaction(valor, dataHora);
        when(repository.save(transaction)).thenReturn(savedTransaction);

        
        Transaction result = service.createTransaction(transaction);
        
        assertEquals(savedTransaction, result);
        verify(repository).save(transaction);
    }

    @Test
    @DisplayName("Should not create transaction when valor is null")
    public void createTransactionCase2() {
        Instant dataHora = Instant.now();
        Transaction transaction = new Transaction(null, dataHora);

        assertThrows(NullPointerException.class, () -> service.createTransaction(transaction));
        verify(repository, never()).save(transaction);
    }

    @Test
    @DisplayName("Should not create transaction when dataHora is null")
    public void createTransactionCase3() {
        Double valor = 100.0;
        Transaction transaction = new Transaction(valor, null);
        
        assertThrows(NullPointerException.class, () -> service.createTransaction(transaction));
        verify(repository, never()).save(transaction);
    }


    @Test
    @DisplayName("Should not create transaction when valor and dataHora are null")
    public void createTransactionCase4() {
        Transaction transaction = new Transaction(null, null);
        assertThrows(NullPointerException.class, () -> service.createTransaction(transaction));
        verify(repository, never()).save(transaction);
    }

    @Test
    @DisplayName("Should not create transaction if valor is negative")
    public void createTransactionCase5() {
        Double valor = -100.0;
        Instant dataHora = Instant.now();
        Transaction transaction = new Transaction(valor, dataHora);

        assertThrows(IllegalArgumentException.class, () -> service.createTransaction(transaction));
        verify(repository, never()).save(transaction);
        
    }
}