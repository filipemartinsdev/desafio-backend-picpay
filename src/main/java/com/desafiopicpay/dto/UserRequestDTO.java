package com.desafiopicpay.dto;


import com.desafiopicpay.entity.UserType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

/**
 * User Request DTO of API
 */
@Data
@Builder @Getter
@AllArgsConstructor
public class UserRequestDTO {
    public UserRequestDTO(){}

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String document;

    @NotNull
    private String email;

    @NotNull
    private String password;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    @NotNull
    private BigDecimal balance;
}
