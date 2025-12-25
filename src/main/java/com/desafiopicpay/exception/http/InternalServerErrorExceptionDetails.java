package com.desafiopicpay.exception.http;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class InternalServerErrorExceptionDetails {
    private final int statusCode;
    private final String details;
    private final LocalDateTime timestamp;

    public InternalServerErrorExceptionDetails(InternalServerErrorException exception){
        this.statusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
        this.details = exception.getMessage();
        this.timestamp = LocalDateTime.now();
    }
}