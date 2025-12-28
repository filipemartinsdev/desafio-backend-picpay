package com.desafiopicpay.exception.http;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

@Getter
public class NotFoundException extends HttpStatusException{
    public NotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND.value());
    }
}
