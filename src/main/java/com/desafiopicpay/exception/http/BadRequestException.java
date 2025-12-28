package com.desafiopicpay.exception.http;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BadRequestException extends HttpStatusException {
    public BadRequestException(String message) {
        super(message, HttpStatus.INTERNAL_SERVER_ERROR.value());

    }
}
