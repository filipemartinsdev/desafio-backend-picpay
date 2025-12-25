package com.desafiopicpay.exception.http;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ServiceUnavailableExceptionDetails {
    private final int statusCode;
    private final String details;
    private final LocalDateTime timestamp;

    public ServiceUnavailableExceptionDetails(ServiceUnavailableException exception){
        this.statusCode = HttpStatus.SERVICE_UNAVAILABLE.value();
        this.details = exception.getMessage();
        this.timestamp = LocalDateTime.now();
    }
}

