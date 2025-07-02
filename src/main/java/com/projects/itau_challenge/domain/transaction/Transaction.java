package com.projects.itau_challenge.domain.transaction;

import java.time.Instant;
import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Schema(name = "Transaction request", description = "Object to register a transaction")
public class Transaction {

    public Transaction(Double valor, Instant dataHora) {
        this.valor = valor;
        this.dataHora = dataHora;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Schema(description = "Transaction value", example = "120.50", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "Value cannot be negative")
    private Double valor;

    @Schema(description = "Data e hora em que a transação ocorreu, em formato UTC.", example = "2025-05-02T16:30:00.00Z", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "Value cannot be negative")
    private Instant dataHora;
    
}