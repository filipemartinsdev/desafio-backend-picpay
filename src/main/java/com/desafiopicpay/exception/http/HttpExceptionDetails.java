package com.desafiopicpay.exception.http;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class HttpExceptionDetails {

    private final int statusCode;
    private final String details;
    private final LocalDateTime timestamp;

    public HttpExceptionDetails(HttpStatusCodeException exception){
        this.statusCode = exception.getStatusCode().value();
        this.details = exception.getMessage();
        this.timestamp = LocalDateTime.now();
    }
}