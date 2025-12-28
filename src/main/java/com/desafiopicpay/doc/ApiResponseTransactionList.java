package com.desafiopicpay.doc;

import com.desafiopicpay.dto.TransactionResponseDTO;
import com.desafiopicpay.dto.UserResponseDTO;
import lombok.Data;

import java.util.List;

@Data
public class ApiResponseTransactionList {
    private String status;
    private String message;
    private List<TransactionResponseDTO> data;
}
