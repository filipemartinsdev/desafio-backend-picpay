package com.desafiopicpay.exception.http;

import lombok.Getter;

@Getter
public class HttpStatusException extends RuntimeException {
    private final int statusCode;

    public HttpStatusException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }
}
