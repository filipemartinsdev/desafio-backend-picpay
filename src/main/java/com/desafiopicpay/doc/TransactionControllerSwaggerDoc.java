package com.desafiopicpay.doc;

import com.desafiopicpay.dto.ApiResponseDTO;
import com.desafiopicpay.dto.TransactionRequestDTO;
import com.desafiopicpay.dto.TransactionResponseDTO;
import com.desafiopicpay.controller.TransactionController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * Interface to document endpoints of {@link TransactionController} using Swagger.
 * @author
 */
public interface TransactionControllerSwaggerDoc {
    @Operation(
            summary = "Get all transactions",
            description = "Retrieve a complete list of transactions in the system"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Retrieved all transactions successfully",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ApiResponseTransactionList.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Intern server error",
                    content = @Content
            )
    })
    public ResponseEntity<ApiResponseDTO<List<TransactionResponseDTO>>> transactionGet();


    @Operation(
            summary = "Get transaction by id"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Retrieved transaction successfully",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ApiResponseTransaction.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid id",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ApiResponseException.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Transaction not exists",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ApiResponseException.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Intern server error",
                    content = @Content
            )
    })
    public ResponseEntity<ApiResponseDTO<TransactionResponseDTO>> transactionGet(@PathVariable Long id);


    @Operation(
            summary = "Handle new transaction request",
            description = "Validate, authenticate and create new transaction"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Create transaction successfully",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ApiResponseTransaction.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid transaction",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ApiResponseException.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Transaction forbidden",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ApiResponseException.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "503",
                    description = "Internal service unavailable",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ApiResponseException.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Intern server error",
                    content = @Content
            )
    })
    public ResponseEntity<ApiResponseDTO<TransactionResponseDTO>> transfer(@Valid @RequestBody TransactionRequestDTO transactionRequest);

}
