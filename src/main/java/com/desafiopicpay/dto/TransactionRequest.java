package com.desafiopicpay.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;

@Data
@Getter
public class TransactionRequest {
    @NotNull @Positive
    private BigDecimal amount;

    @NotNull
    private Long sender;

    @NotNull
    private Long receiver;
}