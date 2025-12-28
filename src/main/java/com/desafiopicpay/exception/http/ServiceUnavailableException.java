package com.desafiopicpay.exception.http;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ServiceUnavailableException extends HttpStatusException {
    public ServiceUnavailableException(String message) {
        super(message, HttpStatus.SERVICE_UNAVAILABLE.value());
    }
}
