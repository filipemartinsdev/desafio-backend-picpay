package com.desafiopicpay.doc;

import com.desafiopicpay.model.dto.TransactionResponseDTO;
import lombok.Data;

import java.util.List;

@Data
public class ApiResponseTransaction {
    private String status;
    private String message;
    private List<TransactionResponseDTO> data;
}
