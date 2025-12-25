package com.desafiopicpay.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name="transactions")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private BigDecimal amount;

    @NotNull
    @ManyToOne @JoinColumn(name="user_sender_id")
    private User sender;

    @NotNull
    @ManyToOne @JoinColumn(name="user_receiver_id")
    private User receiver;

    @NotNull @Column(name="created_at")
    private LocalDateTime timestamp;

    public Transaction(User sender, User receiver, BigDecimal amount){
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
    }
}
