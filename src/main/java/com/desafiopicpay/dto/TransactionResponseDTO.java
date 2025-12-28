package com.desafiopicpay.dto;

import com.desafiopicpay.entity.Transaction;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * DTO for API Response of a Transaction.
 * @author Filipe Martiins
 */
@Data
@Getter
@AllArgsConstructor
public class TransactionResponseDTO {
    private Long id;

    @NotNull
    private BigDecimal amount;

    @NotNull
    private Long sender;

    @NotNull
    private Long receiver;

    @NotNull
    private LocalDateTime timestamp;

    /**
     * Create a Transaction Response DTO from Transaction Entity.
     * @param transaction the Transaction Entity
     * @see Transaction
     */
    public TransactionResponseDTO(Transaction transaction){
        this.id = transaction.getId();
        this.amount = transaction.getAmount();
        this.sender = transaction.getSender().getId();
        this.receiver = transaction.getReceiver().getId();
        this.timestamp = transaction.getTimestamp();
    }
}