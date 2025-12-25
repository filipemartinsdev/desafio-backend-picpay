package com.desafiopicpay.exception.http;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class BadRequestExceptionDetails {
    private final int statusCode;
    private final String details;
    private final LocalDateTime timestamp;

    public BadRequestExceptionDetails(BadRequestException exception){
        this.statusCode = HttpStatus.BAD_REQUEST.value();
        this.details = exception.getMessage();
        this.timestamp = LocalDateTime.now();
    }

    public BadRequestExceptionDetails(String details){
        this.statusCode = HttpStatus.BAD_REQUEST.value();
        this.details = details;
        this.timestamp = LocalDateTime.now();
    }


}
