package com.desafiopicpay.mapper;

import com.desafiopicpay.model.dto.UserRequestDTO;
import com.desafiopicpay.model.dto.UserResponseDTO;
import com.desafiopicpay.model.entity.User;
import com.desafiopicpay.model.entity.UserType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {

    @Test @DisplayName("Should map successfully UserRequestDTO to User")
    void toEntity() {
        var userRequest = new UserRequestDTO();
        userRequest.setFirstName("Filipe");
        userRequest.setLastName("Martins");
        userRequest.setEmail("email@gmail.com");
        userRequest.setPassword("password");
        userRequest.setDocument("12312312312");
        userRequest.setUserType(UserType.COMMON);
        userRequest.setBalance(new BigDecimal(0));

        var spectedEntity = new User();
        spectedEntity.setFirstName("Filipe");
        spectedEntity.setLastName("Martins");
        spectedEntity.setEmail("email@gmail.com");
        spectedEntity.setPassword("password");
        spectedEntity.setDocument("12312312312");
        spectedEntity.setUserType(UserType.COMMON);
        spectedEntity.setBalance(new BigDecimal(0));

        User userEntity = UserMapper.toEntity(userRequest);

        assertTrue(userEntity.equals(spectedEntity));
    }

    @Test @DisplayName("Should map successfully User to UserResponseDTO")
    void toUserResponse() {
        var userEntity = new User();
        userEntity.setId(1L);
        userEntity.setFirstName("Filipe");
        userEntity.setLastName("Martins");
        userEntity.setEmail("email@gmail.com");
        userEntity.setPassword("password");
        userEntity.setDocument("12312312312");
        userEntity.setUserType(UserType.COMMON);
        userEntity.setBalance(new BigDecimal(0));

        var spectedUserResponse = new UserResponseDTO();
        spectedUserResponse.setId(1L);
        spectedUserResponse.setFirstName("Filipe");
        spectedUserResponse.setLastName("Martins");
        spectedUserResponse.setEmail("email@gmail.com");
        spectedUserResponse.setDocument("12312312312");
        spectedUserResponse.setUserType(UserType.COMMON);
        spectedUserResponse.setBalance(new BigDecimal(0));

        UserResponseDTO userResponse = UserMapper.toUserResponse(userEntity);

        assertTrue(userResponse.equals(spectedUserResponse));
    }

    @Test @DisplayName("Should map successfully User to UserRequestDTO")
    void toUserRequest() {
        var userEntity = new User();
        userEntity.setId(1L);
        userEntity.setFirstName("Filipe");
        userEntity.setLastName("Martins");
        userEntity.setEmail("email@gmail.com");
        userEntity.setPassword("password");
        userEntity.setDocument("12312312312");
        userEntity.setUserType(UserType.COMMON);
        userEntity.setBalance(new BigDecimal(0));

        var spectedUserRequest = new UserRequestDTO();
        spectedUserRequest.setFirstName("Filipe");
        spectedUserRequest.setLastName("Martins");
        spectedUserRequest.setEmail("email@gmail.com");
        spectedUserRequest.setDocument("12312312312");
        spectedUserRequest.setUserType(UserType.COMMON);
        spectedUserRequest.setBalance(new BigDecimal(0));

        UserRequestDTO userRequest = UserMapper.toUserRequest(userEntity);

        assertTrue(userRequest.equals(spectedUserRequest));
    }
}