package com.desafiopicpay.dto;

import com.desafiopicpay.entity.User;
import com.desafiopicpay.entity.UserType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;

/**
 * DTO for API response user.
 * <p>This is obtained from a {@link User} entity.
 * @author Filipe Martins
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
    private BigDecimal balance;

    @NotNull
    @Enumerated(EnumType.STRING)
    private UserType userType;

    /**
     * Constructor to create a UserResponseDTO from a User entity.
     * @param user the user entity
     */
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
