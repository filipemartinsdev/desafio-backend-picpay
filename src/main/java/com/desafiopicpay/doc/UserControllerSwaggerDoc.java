package com.desafiopicpay.doc;

import com.desafiopicpay.controller.UserController;
import com.desafiopicpay.dto.ApiResponseDTO;
import com.desafiopicpay.dto.PagedUsersResponseDTO;
import com.desafiopicpay.dto.UserRequestDTO;
import com.desafiopicpay.dto.UserResponseDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


/**
 * Interface to document endpoints of {@link UserController} using Swagger.
 * @author
 */
public interface UserControllerSwaggerDoc {
    @Operation(
            summary = "Get all users paginated",
            description = "Retrieves a page of the users registered in the system"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Users retrieved successfully",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ApiResponsePagedUsers.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content
            )
    })
    public ResponseEntity<ApiResponseDTO<PagedUsersResponseDTO>> getAllUsers(Pageable pageable);


    @Operation(
            summary = "Get user by ID"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "User retrieved successfully",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(
                                    implementation = ApiResponseUser.class
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not exists",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ApiResponseException.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid id",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ApiResponseException.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Intern server error",
                    content = @Content
            )
    })
    public ResponseEntity<ApiResponseDTO<UserResponseDTO>> getUser(@PathVariable Long id);


    @Operation(
            summary = "Get user by Document",
            description = "Get user details by document (CPF/CNPJ)"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Return user",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(
                                    implementation = ApiResponseUser.class
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not exists",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ApiResponseException.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid document",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ApiResponseException.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Intern server error",
                    content = @Content
            )
    })
    public ResponseEntity<ApiResponseDTO<UserResponseDTO>> getUser(@PathVariable String document);


    @Operation(
            summary = "Create new user"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "User created successfully",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(
                                    implementation = ApiResponseUser.class
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid user",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(
                                    implementation = ApiResponseException.class
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Intern server error",
                    content = @Content
            ),
    })
    public ResponseEntity<ApiResponseDTO<UserResponseDTO>> createUser(@RequestBody @Valid UserRequestDTO userRequest);


    @Operation(
            summary = "Replace existent user"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Replace user successfully",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(
                                    implementation = ApiResponseUser.class
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid user",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(
                                    implementation = ApiResponseException.class
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not exists",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(
                                    implementation = ApiResponseException.class
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Intern server error",
                    content = @Content
            )
    })
    public ResponseEntity<ApiResponseDTO<UserResponseDTO>> replaceUser(@PathVariable Long id, @RequestBody @Valid UserRequestDTO userRequest);


    @Operation(
            summary = "Delete user by id"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Delete user successfully"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "invalid id",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(
                                    implementation = ApiResponseException.class
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not exists",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(
                                    implementation = ApiResponseException.class
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Intern server error",
                    content = @Content
            )
    })
    public ResponseEntity<ApiResponseDTO<Void>> deleteUser(@PathVariable Long id);
}
