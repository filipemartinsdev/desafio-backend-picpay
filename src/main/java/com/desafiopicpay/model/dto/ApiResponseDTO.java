package com.desafiopicpay.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

/**
 * DTO for standardized API responses following the JSend specification.
 * <p> Response types: </p>
 *     <ul>
 *         <li><b>success</b>: Request succeeded</li>
 *         <li><b>fail</b>: Request fail due to client error (validation, business rule)</li>
 *         <li><b>error</b>: Request error due to server error</li>
 *     </ul>
 *
 *
 * @param <T> The type of the response data
 */
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponseDTO<T> {
    private final String status;
    private final String message;

    @Schema(description = "Response data")
    private final T data;

    private ApiResponseDTO(String status, T data, String message){
        this.status = status;
        this.data = data;
        this.message = message;
    }

    /**
     * <p>DTO Factory - Create default success Response DTO</p>
     * <p>Use this for operations that succeeded and return content (e.g., POST operations)</p>
     * @param data Generic response data
     * @return ApiResponse with success status
     * @param <T> Type of response data
     */
    public static <T> ApiResponseDTO<T> success(T data){
        return new ApiResponseDTO<>("success", data, null);
    }

    /**
     * <p>DTO Factory - Create default success Response DTO</p>
     * <p>Use this for operations that succeeded but don't return content (e.g., DELETE operations)</p>
     * @return ApiResponse with success status
     */
    public static ApiResponseDTO<Void> success(){
        return new ApiResponseDTO<>("success", null, null);
    }

    /**
     * <p>DTO Factory - Create default fail Response DTO</p>
     * <p>Use this for operations that failed and return content (e.g., BAD_REQUEST)</p>
     * @param data Type of response data
     * @return ApiResponse with fail status and data
     * @param <T> Type of response data
     */
    public static <T> ApiResponseDTO<T> fail(T data){
        return new ApiResponseDTO<>("fail", data, null);
    }

    /**
     * <p>DTO Factory - Create default fail Response DTO</p>
     * <p>Use this for operations that failed and return content and extra message</p>
     * @param data Type of response data
     * @param message Fail message
     * @return ApiResponse with fail status, data and message
     * @param <T> Type of response data
     */
    public static <T> ApiResponseDTO<T> fail(T data, String message){
        return new ApiResponseDTO<>("fail", data, message);
    }

    /**
     * <p>DTO Factory - Create default error Response DTO</p>
     * <p>Use this for operations that failed by server error and return content</p>
     * @param data Generic response data
     * @return ApiResponse with error status and data
     * @param <T> Type of response data
     */
    public static <T> ApiResponseDTO<T> error(T data){
        return new ApiResponseDTO<>("error", data, null);
    }

    /**
     * <p>DTO Factory - Create default error Response DTO</p>
     * <p>Use this for operations that failed by server error and return content and message</p>
     * @param data Generic response data
     * @param message Error message
     * @return ApiResponse with error status, data and message
     * @param <T> Type of response data
     */
    public static <T> ApiResponseDTO<T> error(T data, String message){
        return new ApiResponseDTO<>("error", data, message);
    }
}
