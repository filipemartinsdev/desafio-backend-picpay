package com.desafiopicpay.exception.http;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class MethodNotAllowedExceptionDetails {
    private final int statusCode;
    private final String details;
    private final LocalDateTime timestamp;

    public MethodNotAllowedExceptionDetails(String message){
        this.statusCode = HttpStatus.METHOD_NOT_ALLOWED.value();
        this.details = message;
        this.timestamp = LocalDateTime.now();
    }
}