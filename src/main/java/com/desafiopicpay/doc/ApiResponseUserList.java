package com.desafiopicpay.doc;

import com.desafiopicpay.dto.UserResponseDTO;
import lombok.Data;

import java.util.List;

@Data
public class ApiResponseUserList {
    private String status;
    private String message;
    private List<UserResponseDTO> data;
}
