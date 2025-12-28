package com.desafiopicpay.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;

/**
 * DTO for API Requested transaction.
 * @author Filipe Martins
 */
@Data
@Getter
public class TransactionRequestDTO {
    @NotNull @Positive
    private BigDecimal amount;

    @NotNull
    private Long sender;

    @NotNull
    private Long receiver;
}