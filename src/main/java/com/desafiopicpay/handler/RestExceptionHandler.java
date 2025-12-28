package com.desafiopicpay.handler;

import com.desafiopicpay.dto.ApiResponseDTO;
import com.desafiopicpay.exception.http.*;
import com.desafiopicpay.exception.transaction.TransactionForbiddenException;
import com.desafiopicpay.exception.transaction.TransactionForbiddenExceptionDetails;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.server.MethodNotAllowedException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<@NonNull ApiResponseDTO<HttpExceptionDetails>> notFoundExceptionHandler(NotFoundException exception){
        return ResponseEntity
                .status(exception.getStatusCode())
                .body(ApiResponseDTO.fail(
                        new HttpExceptionDetails(exception)
                ));
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<@NonNull ApiResponseDTO<HttpExceptionDetails>> badRequestExceptionHandler(BadRequestException exception){
        return ResponseEntity
                .status(exception.getStatusCode())
                .body(ApiResponseDTO.fail(
                        new HttpExceptionDetails(exception)
                ));
    }

    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<@NonNull ApiResponseDTO<HttpExceptionDetails>> internalServerErrorExceptionHandler(InternalServerErrorException exception){
        return ResponseEntity
                .status(exception.getStatusCode())
                .body(ApiResponseDTO.fail(
                        new HttpExceptionDetails(exception)
                ));
    }

    @ExceptionHandler(ServiceUnavailableException.class)
    public ResponseEntity<@NonNull ApiResponseDTO<HttpExceptionDetails>> serviceUnavailableExceptionHandler(ServiceUnavailableException exception){
        return ResponseEntity
                .status(exception.getStatusCode())
                .body(ApiResponseDTO.fail(
                        new HttpExceptionDetails(exception)
                ));
    }

    @ExceptionHandler(TransactionForbiddenException.class)
    public ResponseEntity<@NonNull ApiResponseDTO<HttpExceptionDetails>> transactionForbiddenExceptionHandler(TransactionForbiddenException exception){
        return ResponseEntity
                .status(exception.getStatusCode())
                .body(ApiResponseDTO.fail(
                        new HttpExceptionDetails(exception)
                ));
    }

    @ExceptionHandler(HttpStatusCodeException.class)
    public ResponseEntity<@NonNull ApiResponseDTO<HttpExceptionDetails>> genericHttpStatusExceptionHandler(HttpStatusCodeException exception){
        return ResponseEntity
                .status(exception.getStatusCode())
                .body(ApiResponseDTO.fail(
                        new HttpExceptionDetails(exception)
                ));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<@NonNull ApiResponseDTO<HttpExceptionDetails>> dataIntegrityViolationExceptionHandler(DataIntegrityViolationException exception){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponseDTO.fail(
                    new HttpExceptionDetails(exception.getMessage(), HttpStatus.BAD_REQUEST.value())
                ));
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<@NonNull ApiResponseDTO<HttpExceptionDetails>> noResourceExceptionHandler(NoResourceFoundException exception){
        return ResponseEntity
                .status(exception.getStatusCode())
                .body(ApiResponseDTO.fail(
                        new HttpExceptionDetails(exception.getMessage(), exception.getStatusCode().value())
                ));
    }

    @ExceptionHandler(MethodNotAllowedException.class)
    public ResponseEntity<@NonNull ApiResponseDTO<HttpExceptionDetails>> methodNotAllowedExceptionHandler(MethodNotAllowedException exception){
        return ResponseEntity
                .status(exception.getStatusCode())
                .body(ApiResponseDTO.fail(
                        new HttpExceptionDetails(exception.getMessage(), exception.getStatusCode().value())
                ));
    }
}
