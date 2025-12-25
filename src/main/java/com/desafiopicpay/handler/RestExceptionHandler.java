package com.desafiopicpay.handler;

import com.desafiopicpay.dto.ApiResponse;
import com.desafiopicpay.exception.http.*;
import com.desafiopicpay.exception.transaction.TransactionForbiddenException;
import com.desafiopicpay.exception.transaction.TransactionForbiddenExceptionDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.server.MethodNotAllowedException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiResponse<NotFoundExceptionDetails>> notFoundExceptionHandler(NotFoundException exception){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.fail(
                        new NotFoundExceptionDetails(exception)
                ));
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiResponse<BadRequestExceptionDetails>> badRequestExceptionHandler(BadRequestException exception){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.fail(
                        new BadRequestExceptionDetails(exception)
                ));
    }

    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<ApiResponse<InternalServerErrorExceptionDetails>> badRequestExceptionHandler(InternalServerErrorException exception){
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.fail(
                        new InternalServerErrorExceptionDetails(exception)
                ));
    }

    @ExceptionHandler(ServiceUnavailableException.class)
    public ResponseEntity<ApiResponse<ServiceUnavailableExceptionDetails>> badRequestExceptionHandler(ServiceUnavailableException exception){
        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(ApiResponse.fail(
                        new ServiceUnavailableExceptionDetails(exception)
                ));
    }

    @ExceptionHandler(TransactionForbiddenException.class)
    public ResponseEntity<ApiResponse<TransactionForbiddenExceptionDetails>> TransactionForbiddenExceptionHandler(TransactionForbiddenException exception){
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(ApiResponse.fail(
                        new TransactionForbiddenExceptionDetails(exception)
                ));
    }

    @ExceptionHandler(HttpStatusCodeException.class)
    public ResponseEntity<ApiResponse<HttpExceptionDetails>> genericHttpStatusExceptionHandler(HttpStatusCodeException exception){
        return ResponseEntity
                .status(exception.getStatusCode())
                .body(ApiResponse.fail(
                        new HttpExceptionDetails(exception)
                ));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiResponse<BadRequestExceptionDetails>> DataIntegrityViolationExceptionHandler(DataIntegrityViolationException exception){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.fail(
                        new BadRequestExceptionDetails(exception.getLocalizedMessage())
                ));
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ApiResponse<NotFoundExceptionDetails>> DataIntegrityViolationExceptionHandler(NoResourceFoundException exception){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.fail(
                        new NotFoundExceptionDetails(exception.getMessage())
                ));
    }

    @ExceptionHandler(MethodNotAllowedException.class)
    public ResponseEntity<ApiResponse<MethodNotAllowedExceptionDetails>> MethodNotAllowedExceptionHandler(MethodNotAllowedException exception){
        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(ApiResponse.fail(
                        new MethodNotAllowedExceptionDetails(exception.getMessage())
                ));
    }
}
