package com.desafiopicpay.controller;

import com.desafiopicpay.doc.UserControllerSwaggerDoc;
import com.desafiopicpay.dto.ApiResponseDTO;
import com.desafiopicpay.dto.PagedUsersResponseDTO;
import com.desafiopicpay.dto.UserRequestDTO;
import com.desafiopicpay.dto.UserResponseDTO;
import com.desafiopicpay.entity.User;
import com.desafiopicpay.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * User controller, following the JSend standard response.
 * <p>Endpoints to:
 * <ul>
 *     <li>Get all users</li>
 *     <li>Get user by id</li>
 *     <li>Create new user</li>
 *     <li>Replace existent user</li>
 *     <li>Delete user by id</li>
 * </ul>
 * @see ApiResponseDTO
 * @see UserRequestDTO
 * @see UserResponseDTO
 * @see UserService
 * @author Filipe Martins
 */
@RestController
@RequestMapping("/api/v1/users")
public class UserController implements UserControllerSwaggerDoc {
    /**
     * UserService auto-injected by Spring Framework
     * @see Autowired
     */
    @Autowired
    private UserService userService;

    
    /**
     * Retrieve a paged list of users with their basic details.
     *
     * <p>The response is wrapped in {@link ApiResponseDTO} for standardized format.
     * @return {@link ResponseEntity} containing {@link ApiResponseDTO} with {@link PagedUsersResponseDTO} on field data
     * @see UserService#getAll(Pageable)
     */
    @GetMapping("")
    public ResponseEntity<ApiResponseDTO<PagedUsersResponseDTO>> getAllUsers(Pageable pageable){
        return ResponseEntity.ok(
                ApiResponseDTO.success(
                        this.userService.getAll(pageable)
                )
        );
    }

    /**
     * Retrieves a user from its id.
     * <p>The response is wrapped in {@link ApiResponseDTO} for standardize format.
     * @param id the user id
     * @return {@link ResponseEntity} containing {@link ApiResponseDTO} with {@link UserResponseDTO} on field data
     * @see UserService#getById(Long)  
     */
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<UserResponseDTO>> getUser(@PathVariable Long id){
        return ResponseEntity.ok(
                ApiResponseDTO.success(
                        this.userService.getById(id)
                )
        );
    }

    
    /**
     * Retrieves a user from it document.
     * <p>The response is wrapped in {@link ApiResponseDTO} for standardize format.
     * @param document the user document (CPF/CNPJ)
     * @return {@link ResponseEntity} containing {@link ApiResponseDTO} with {@link UserResponseDTO} on field data
     * @see UserService#getByDocument(String)
     */
    @GetMapping("/document/{document}")
    public ResponseEntity<ApiResponseDTO<UserResponseDTO>> getUser(@PathVariable String document){
        return ResponseEntity.ok(
                ApiResponseDTO.success(
                        this.userService.getByDocument(document)
                )
        );
    }


    /**
     * Replace an existent user.
     * @param id the user id
     * @param userRequest the new user entity
     * @return {@link ResponseEntity} containing {@link ApiResponseDTO} with the requested user on field data
     * @see UserService#replace(Long, UserRequestDTO) 
     */
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<UserResponseDTO>> replaceUser(@PathVariable Long id, @RequestBody @Valid UserRequestDTO userRequest){
        return ResponseEntity.ok(
                ApiResponseDTO.success(
                        this.userService.replace(id, userRequest)
                )
        );
    }


    /**
     * Create new user on the system.
     * @param userRequest the requested user
     * @return {@link ResponseEntity} containing {@link ApiResponseDTO} with the created user on field data
     * @see UserService#save(User) 
     */
    @PostMapping("")
    public ResponseEntity<ApiResponseDTO<UserResponseDTO>> createUser(@RequestBody @Valid UserRequestDTO userRequest){
        return ResponseEntity.ok(
                ApiResponseDTO.success(
                        this.userService.save(new User(userRequest))
                )
        );
    }


    /**
     * Delete a user from the system
     * @param id the user id
     * @return {@link ResponseEntity} containing {@link ApiResponseDTO} without field data
     * @see UserService#delete(Long) 
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<Void>> deleteUser(@PathVariable Long id){
        this.userService.delete(id);

        return ResponseEntity.ok(
                ApiResponseDTO.success()
        );
    }
}
