package com.desafiopicpay.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    private final String status;
    private final String message;
    private final T data;

    private ApiResponse(String status, T data, String message){
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static <T> ApiResponse<T> success(T data){
        return new ApiResponse<>("success", data, null);
    }

    public static ApiResponse<Void> success(){
        return new ApiResponse<>("success", null, null);
    }

    public static <T> ApiResponse<T> fail(T data){
        return new ApiResponse<>("fail", data, null);
    }

    public static <T> ApiResponse<T> fail(T data, String message){
        return new ApiResponse<>("fail", data, message);
    }

    public static <T> ApiResponse<T> error(T data){
        return new ApiResponse<>("error", data, null);
    }

    public static <T> ApiResponse<T> error(T data, String message){
        return new ApiResponse<>("error", data, message);
    }
}
