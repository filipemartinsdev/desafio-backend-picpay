package com.desafiopicpay.exception.transaction;

import com.desafiopicpay.exception.http.HttpStatusException;
import org.springframework.http.HttpStatus;

public class TransactionForbiddenException extends HttpStatusException {
    public TransactionForbiddenException(String message) {
        super(message, HttpStatus.FORBIDDEN.value());
    }
}
