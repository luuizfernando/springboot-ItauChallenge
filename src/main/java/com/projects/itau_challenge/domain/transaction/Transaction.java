package com.projects.itau_challenge.domain.transaction;

import java.time.Instant;
import java.util.UUID;

import com.projects.itau_challenge.domain.user.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(name="tb_transaction")
public class Transaction {

    public Transaction(Double valor, Instant dataHora) {
        this.valor = valor;
        this.dataHora = dataHora;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull
    private Double valor;

    @NotNull
    private Instant dataHora;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
}