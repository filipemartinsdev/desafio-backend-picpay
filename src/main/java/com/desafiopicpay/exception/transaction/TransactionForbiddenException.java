package com.desafiopicpay.exception.transaction;

public class TransactionForbiddenException extends RuntimeException {
    public TransactionForbiddenException(String message) {
        super(message);
    }
}
