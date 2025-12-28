package com.desafiopicpay.exception.http;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class HttpExceptionDetails {

    private final String details;
    private final int statusCode;
    private final LocalDateTime timestamp;

    public HttpExceptionDetails(HttpStatusCodeException exception){
        this.statusCode = exception.getStatusCode().value();
        this.details = exception.getMessage();
        this.timestamp = LocalDateTime.now();
    }

    public HttpExceptionDetails(HttpStatusException exception){
        this.details = exception.getMessage();
        this.statusCode = exception.getStatusCode();
        this.timestamp = LocalDateTime.now();
    }

    public HttpExceptionDetails(String details, int statusCode){
        this.details = details;
        this.statusCode = statusCode;
        this.timestamp = LocalDateTime.now();
    }
}