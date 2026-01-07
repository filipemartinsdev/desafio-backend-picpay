package com.desafiopicpay.doc;

import com.desafiopicpay.model.dto.UserResponseDTO;
import lombok.Data;

@Data
public class ApiResponseUser {
    private String status;
    private String message;
    private UserResponseDTO data;
}
