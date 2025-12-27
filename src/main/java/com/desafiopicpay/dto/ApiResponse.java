package com.desafiopicpay.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

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
public class ApiResponse<T> {
    private final String status;
    private final String message;
    private final T data;

    private ApiResponse(String status, T data, String message){
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
    public static <T> ApiResponse<T> success(T data){
        return new ApiResponse<>("success", data, null);
    }

    /**
     * <p>DTO Factory - Create default success Response DTO</p>
     * <p>Use this for operations that succeeded but don't return content (e.g., DELETE operations)</p>
     * @return ApiResponse with success status
     */
    public static ApiResponse<Void> success(){
        return new ApiResponse<>("success", null, null);
    }

    /**
     * <p>DTO Factory - Create default fail Response DTO</p>
     * <p>Use this for operations that failed and return content (e.g., BAD_REQUEST)</p>
     * @param data Type of response data
     * @return ApiResponse with fail status and data
     * @param <T> Type of response data
     */
    public static <T> ApiResponse<T> fail(T data){
        return new ApiResponse<>("fail", data, null);
    }

    /**
     * <p>DTO Factory - Create default fail Response DTO</p>
     * <p>Use this for operations that failed and return content and extra message</p>
     * @param data Type of response data
     * @param message Fail message
     * @return ApiResponse with fail status, data and message
     * @param <T> Type of response data
     */
    public static <T> ApiResponse<T> fail(T data, String message){
        return new ApiResponse<>("fail", data, message);
    }

    /**
     * <p>DTO Factory - Create default error Response DTO</p>
     * <p>Use this for operations that failed by server error and return content</p>
     * @param data Generic response data
     * @return ApiResponse with error status and data
     * @param <T> Type of response data
     */
    public static <T> ApiResponse<T> error(T data){
        return new ApiResponse<>("error", data, null);
    }

    /**
     * <p>DTO Factory - Create default error Response DTO</p>
     * <p>Use this for operations that failed by server error and return content and message</p>
     * @param data Generic response data
     * @param message Error message
     * @return ApiResponse with error status, data and message
     * @param <T> Type of response data
     */
    public static <T> ApiResponse<T> error(T data, String message){
        return new ApiResponse<>("error", data, message);
    }
}
