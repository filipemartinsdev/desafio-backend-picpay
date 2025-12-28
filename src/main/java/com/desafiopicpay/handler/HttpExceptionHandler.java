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

/**
 * The Exception Handler of the application.
 * <p>This supports both UserController adn TransactionController.
 */
@ControllerAdvice
public class HttpExceptionHandler {

    /**
     * Handler to {@link NotFoundException}.
     * <p>This returns {@code 404 Not Found} HTTP status.
     * @param exception the thrown exception
     * @return {@link ResponseEntity} containing {@link ApiResponseDTO} with the error details.
     */
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<@NonNull ApiResponseDTO<HttpExceptionDetails>> notFoundExceptionHandler(NotFoundException exception){
        return ResponseEntity
                .status(exception.getStatusCode())
                .body(ApiResponseDTO.fail(
                        new HttpExceptionDetails(exception)
                ));
    }

    /**
     * Handler to {@link BadRequestException}.
     * <p>This returns {@code 400 Bad Request} HTTP status.
     * @param exception the thrown exception
     * @return {@link ResponseEntity} containing {@link ApiResponseDTO} with the error details.
     */
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<@NonNull ApiResponseDTO<HttpExceptionDetails>> badRequestExceptionHandler(BadRequestException exception){
        return ResponseEntity
                .status(exception.getStatusCode())
                .body(ApiResponseDTO.fail(
                        new HttpExceptionDetails(exception)
                ));
    }

    /**
     * Handler to {@link InternalServerErrorException}.
     * <p>This returns {@code 500 Internal Server Error} HTTP status.
     * @param exception the thrown exception
     * @return {@link ResponseEntity} containing {@link ApiResponseDTO} with the error details.
     */
    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<@NonNull ApiResponseDTO<HttpExceptionDetails>> internalServerErrorExceptionHandler(InternalServerErrorException exception){
        return ResponseEntity
                .status(exception.getStatusCode())
                .body(ApiResponseDTO.fail(
                        new HttpExceptionDetails(exception)
                ));
    }

    /**
     * Handler to {@link BadRequestException}.
     * <p>This returns {@code 503 Service Unavailable} HTTP status.
     * <p>Used when critical external services is unavailable.
     * @param exception the thrown exception
     * @return {@link ResponseEntity} containing {@link ApiResponseDTO} with the error details.
     * @see com.desafiopicpay.service.AuthorizationService
     * @see com.desafiopicpay.service.NotificationService
     */
    @ExceptionHandler(ServiceUnavailableException.class)
    public ResponseEntity<@NonNull ApiResponseDTO<HttpExceptionDetails>> serviceUnavailableExceptionHandler(ServiceUnavailableException exception){
        return ResponseEntity
                .status(exception.getStatusCode())
                .body(ApiResponseDTO.fail(
                        new HttpExceptionDetails(exception)
                ));
    }

    /**
     * Handler to {@link TransactionForbiddenException}.
     * <p>This returns {@code 403 Forbidden} HTTP status.
     * <p>Used when the transaction is forbidden for any cause.
     * @param exception the thrown exception
     * @return {@link ResponseEntity} containing {@link ApiResponseDTO} with the error details.
     * @see com.desafiopicpay.service.TransactionService
     * @see com.desafiopicpay.service.AuthorizationService
     */
    @ExceptionHandler(TransactionForbiddenException.class)
    public ResponseEntity<@NonNull ApiResponseDTO<HttpExceptionDetails>> transactionForbiddenExceptionHandler(TransactionForbiddenException exception){
        return ResponseEntity
                .status(exception.getStatusCode())
                .body(ApiResponseDTO.fail(
                        new HttpExceptionDetails(exception)
                ));
    }

    /**
     * Handler to {@link HttpStatusCodeException}.
     * <p>This returns any HTTP status, depends on the cause.
     * <p>Used to handle Spring generic HTTP Exception.
     * @param exception the thrown exception
     * @return {@link ResponseEntity} containing {@link ApiResponseDTO} with the error details.
     */
    @ExceptionHandler(HttpStatusCodeException.class)
    public ResponseEntity<@NonNull ApiResponseDTO<HttpExceptionDetails>> genericHttpStatusExceptionHandler(HttpStatusCodeException exception){
        return ResponseEntity
                .status(exception.getStatusCode())
                .body(ApiResponseDTO.fail(
                        new HttpExceptionDetails(exception)
                ));
    }

    /**
     * Handler to {@link DataIntegrityViolationException}.
     * <p>This returns {@code 400 Bad Request} HTTP status.
     * <p>Used to handle Spring generics HTTP Bad Request Exceptions.
     * @param exception the thrown exception
     * @return {@link ResponseEntity} containing {@link ApiResponseDTO} with the error details.
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<@NonNull ApiResponseDTO<HttpExceptionDetails>> dataIntegrityViolationExceptionHandler(DataIntegrityViolationException exception){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponseDTO.fail(
                    new HttpExceptionDetails(exception.getMessage(), HttpStatus.BAD_REQUEST.value())
                ));
    }

    /**
     * Handler to {@link NoResourceFoundException}.
     * <p>This returns {@code 404 Not Found} HTTP status.
     * <p>Used to handle Spring exception thrown when invalid resource path is accessed.
     * @param exception the thrown exception
     * @return {@link ResponseEntity} containing {@link ApiResponseDTO} with the error details.
     */
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<@NonNull ApiResponseDTO<HttpExceptionDetails>> noResourceExceptionHandler(NoResourceFoundException exception){
        return ResponseEntity
                .status(exception.getStatusCode())
                .body(ApiResponseDTO.fail(
                        new HttpExceptionDetails(exception.getMessage(), exception.getStatusCode().value())
                ));
    }

    /**
     * Handler to {@link NoResourceFoundException}.
     * <p>This returns {@code 400 Bad Request} HTTP status.
     * <p>Used to handle Spring exception thrown when an invalid HTTP VERB is requested.
     * @param exception the thrown exception
     * @return {@link ResponseEntity} containing {@link ApiResponseDTO} with the error details.
     */
    @ExceptionHandler(MethodNotAllowedException.class)
    public ResponseEntity<@NonNull ApiResponseDTO<HttpExceptionDetails>> methodNotAllowedExceptionHandler(MethodNotAllowedException exception){
        return ResponseEntity
                .status(exception.getStatusCode())
                .body(ApiResponseDTO.fail(
                        new HttpExceptionDetails(exception.getMessage(), exception.getStatusCode().value())
                ));
    }
}
