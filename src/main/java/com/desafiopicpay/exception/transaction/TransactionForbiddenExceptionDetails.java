package com.desafiopicpay.exception.transaction;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class TransactionForbiddenExceptionDetails {
    private final int statusCode;
    private final String details;
    private final LocalDateTime timestamp;

    public TransactionForbiddenExceptionDetails(TransactionForbiddenException exception){
        this.statusCode = HttpStatus.FORBIDDEN.value();
        this.details = exception.getMessage();
        this.timestamp = LocalDateTime.now();
    }
}
