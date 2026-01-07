package com.desafiopicpay.mapper;


import com.desafiopicpay.dto.UserRequestDTO;
import com.desafiopicpay.dto.UserResponseDTO;
import com.desafiopicpay.entity.User;

/**
 * Util class to mapper User DTO and Entity
 */
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
        userResponseDTO.setUserType(userEntity.getUserType());
        userResponseDTO.setBalance(userEntity.getBalance());
        return userResponseDTO;
    }

    public static UserRequestDTO toUserRequest(User userEntity){
        UserRequestDTO userRequestDTO = new UserRequestDTO();

        userRequestDTO.setFirstName(userEntity.getFirstName());
        userRequestDTO.setLastName(userEntity.getLastName());
        userRequestDTO.setDocument(userEntity.getDocument());
        userRequestDTO.setEmail(userEntity.getEmail());
        userRequestDTO.setBalance(userEntity.getBalance());
        userRequestDTO.setUserType(userEntity.getUserType());
        userRequestDTO.setBalance(userEntity.getBalance());

        return userRequestDTO;
    }
}
