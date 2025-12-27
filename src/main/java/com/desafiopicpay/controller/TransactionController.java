package com.desafiopicpay.controller;

import com.desafiopicpay.dto.ApiResponse;
import com.desafiopicpay.dto.TransactionRequest;
import com.desafiopicpay.dto.TransactionResponse;
import com.desafiopicpay.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @GetMapping("")
    public ResponseEntity<ApiResponse<List<TransactionResponse>>> transactionGet(){
        return ResponseEntity.ok(
                ApiResponse.success(this.transactionService.getAll())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TransactionResponse>> transactionGet(@PathVariable Long id){
        return ResponseEntity.ok(
                ApiResponse.success(
                        this.transactionService.getById(id)
                )
        );
    }

    /**
     * Endpoint to handle transaction request.
     * @param transactionRequest
     * @return API Response with Accepted Transaction
     */
    @PostMapping("/transfer")
    public ResponseEntity<ApiResponse<TransactionResponse>> transfer(@Valid @RequestBody TransactionRequest transactionRequest){
        return ResponseEntity.ok(
                ApiResponse.success(
                        this.transactionService.create(transactionRequest)
                )
        );
    }
}
