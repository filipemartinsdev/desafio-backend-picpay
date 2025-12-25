package com.desafiopicpay.entity;

import com.desafiopicpay.dto.UserRequest;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Entity @Table(name = "users")
@Builder
@Getter
@Setter
@AllArgsConstructor
public class User {
    public User(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @Column(unique=true)
    private String document;

    @NotNull @Column(unique=true)
    private String email;

    @NotNull
    private String password;

    private BigDecimal balance;

    @NotNull
    @Enumerated(EnumType.STRING)
    private UserType userType;

    public User(UserRequest userRequest){
        this.firstName = userRequest.getFirstName();
        this.lastName = userRequest.getLastName();
        this.email = userRequest.getEmail();
        this.password = userRequest.getPassword();
        this.document = userRequest.getDocument();
        this.userType = userRequest.getUserType();
        this.balance = userRequest.getBalance();
    }
}
