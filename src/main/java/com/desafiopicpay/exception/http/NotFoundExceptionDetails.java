package com.desafiopicpay.exception.http;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data @AllArgsConstructor
public class NotFoundExceptionDetails {
    private final int statusCode;
    private final String details;
    private final LocalDateTime timestamp;

    public NotFoundExceptionDetails(NotFoundException exception){
        this.statusCode = HttpStatus.NOT_FOUND.value();
        this.details = exception.getMessage();
        this.timestamp = LocalDateTime.now();
    }

    public NotFoundExceptionDetails(String details){
        this.statusCode = HttpStatus.NOT_FOUND.value();
        this.details = details;
        this.timestamp = LocalDateTime.now();
    }
}
