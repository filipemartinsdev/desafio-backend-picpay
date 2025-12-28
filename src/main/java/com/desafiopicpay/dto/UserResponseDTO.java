package com.desafiopicpay.dto;

import com.desafiopicpay.entity.User;
import com.desafiopicpay.entity.UserType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;

/**
 * User Response DTO of API
 */
@Data
@Builder @Getter
@AllArgsConstructor
public class UserResponseDTO {
    @NotNull
    private Long id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String document;

    @NotNull
    private String email;

    @NotNull
    private UserType userType;

    @NotNull
    private BigDecimal balance;

//    @NotNull
//    @Enumerated(EnumType.STRING)
//    private UserType userType;

    public UserResponseDTO(User user){
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.document = user.getDocument();
        this.email = user.getEmail();
        this.userType = user.getUserType();
        this.balance = user.getBalance();
    }
}
