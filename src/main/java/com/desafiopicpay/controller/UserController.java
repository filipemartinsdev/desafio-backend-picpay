package com.desafiopicpay.controller;

import com.desafiopicpay.dto.ApiResponse;
import com.desafiopicpay.dto.UserRequest;
import com.desafiopicpay.dto.UserResponse;
import com.desafiopicpay.entity.User;
import com.desafiopicpay.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("")
    public ResponseEntity<ApiResponse<List<UserResponse>>> getAllUsers(){
        return ResponseEntity.ok(
                ApiResponse.success(
                        this.userService.getAll()
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponse>> getUser(@PathVariable Long id){
        return ResponseEntity.ok(
                ApiResponse.success(
                        this.userService.getById(id)
                )
        );
    }

    @GetMapping("/document/{document}")
    public ResponseEntity<ApiResponse<UserResponse>> getUser(@PathVariable String document){
        return ResponseEntity.ok(
                ApiResponse.success(
                        this.userService.getByDocument(document)
                )
        );
    }

    @PostMapping("")
    public ResponseEntity<ApiResponse<UserResponse>> createUser(@RequestBody @Valid UserRequest userRequest){
        return ResponseEntity.ok(
                ApiResponse.success(
                        this.userService.save(new User(userRequest))
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponse>> replaceUser(@PathVariable Long id, @RequestBody @Valid UserRequest userRequest){
        return ResponseEntity.ok(
                ApiResponse.success(
                        this.userService.replace(id, userRequest)
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> userPost(@PathVariable Long id){
        this.userService.delete(id);

        return ResponseEntity.ok(
                ApiResponse.success()
        );
    }
}
