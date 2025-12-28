package com.desafiopicpay.controller;

import com.desafiopicpay.doc.TransactionControllerSwaggerDoc;
import com.desafiopicpay.dto.ApiResponseDTO;
import com.desafiopicpay.dto.TransactionRequestDTO;
import com.desafiopicpay.dto.TransactionResponseDTO;
import com.desafiopicpay.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Transactions controller, following the JSend standard response.
 * <p>Endpoints to:
 * <ul>
 *     <li>Get all transactions</li>
 *     <li>Get transaction by id</li>
 *     <li>Solicited new transaction</li>
 * </ul>
 * @see ApiResponseDTO
 * @see TransactionRequestDTO
 * @see TransactionResponseDTO
 * @see TransactionService
 * @author Filipe Martins
 */
@RestController
@RequestMapping("api/v1/transactions")
public class TransactionController implements TransactionControllerSwaggerDoc {
    /**
     * TransactionService auto-injected by Spring Framework.
     * @see Autowired
     */
    @Autowired
    private TransactionService transactionService;


    /**
     * Retrieve a list of all transactions with their basic details.
     * <p>The response is wrapped in {@link ApiResponseDTO} for standardize format.
     * @return {@link ResponseEntity} containing {@link ApiResponseDTO} with list of {@link TransactionResponseDTO} on field data.
     * @see TransactionService#getAll()
     */
    @GetMapping("")
    public ResponseEntity<ApiResponseDTO<List<TransactionResponseDTO>>> transactionGet(){
        return ResponseEntity.ok(
                ApiResponseDTO.success(this.transactionService.getAll())
        );
    }


    /**
     * Retrieve a transaction from its id.
     * <p>The response isi wrapped in {@link ApiResponseDTO} for standardize format.
     * @param id the transaction id
     * @return {@link ResponseEntity} containing {@link ApiResponseDTO} with the transaction on field data.
     * @see TransactionService#getById(Long)
     */
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<TransactionResponseDTO>> transactionGet(@PathVariable Long id){
        return ResponseEntity.ok(
                ApiResponseDTO.success(
                        this.transactionService.getById(id)
                )
        );
    }

    /**
     * Handle new transaction request.
     * <p>The response is wrapped in {@link ApiResponseDTO} for standardize dormat.
     * @param transactionRequest the requested transaction
     * @return {@link ResponseEntity} containing {@link ApiResponseDTO} with the requested transaction on field data
     * @see TransactionService#create(TransactionRequestDTO)
     */
    @PostMapping("/transfer")
    public ResponseEntity<ApiResponseDTO<TransactionResponseDTO>> transfer(@Valid @RequestBody TransactionRequestDTO transactionRequest){
        return ResponseEntity.ok(
                ApiResponseDTO.success(
                        this.transactionService.create(transactionRequest)
                )
        );
    }
}
