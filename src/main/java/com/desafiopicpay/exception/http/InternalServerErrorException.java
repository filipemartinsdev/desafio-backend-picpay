package com.desafiopicpay.exception.http;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class InternalServerErrorException extends HttpStatusException {

    public InternalServerErrorException(String message) {
        super(message, HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
}
