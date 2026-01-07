package com.desafiopicpay.doc;

import com.desafiopicpay.exception.http.HttpExceptionDetails;
import lombok.Data;

@Data
public class ApiResponseException {
    private String status;
    private String message;
    private HttpExceptionDetails data;
}
