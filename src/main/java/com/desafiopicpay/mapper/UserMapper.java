package com.desafiopicpay.mapper;


import com.desafiopicpay.dto.UserRequestDTO;
import com.desafiopicpay.dto.UserResponseDTO;
import com.desafiopicpay.entity.User;

public class UserMapper {
    public static User toEntity(UserRequestDTO userRequestDTO){
        User user = new User();
        user.setFirstName(userRequestDTO.getFirstName());
        user.setLastName(userRequestDTO.getLastName());
        user.setDocument(userRequestDTO.getDocument());
        user.setEmail(userRequestDTO.getEmail());
        user.setPassword(userRequestDTO.getPassword());
        user.setBalance(userRequestDTO.getBalance());
        user.setUserType(userRequestDTO.getUserType());
        return user;
    }

    public static UserResponseDTO toUserResponse(User userEntity){
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(userEntity.getId());
        userResponseDTO.setFirstName(userEntity.getFirstName());
        userResponseDTO.setLastName(userEntity.getLastName());
        userResponseDTO.setDocument(userEntity.getDocument());
        userResponseDTO.setEmail(userEntity.getEmail());
        return userResponseDTO;
    }
}
