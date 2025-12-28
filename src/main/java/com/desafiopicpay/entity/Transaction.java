package com.desafiopicpay.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Transaction Entity of Database.
 */
@Entity
@Table(name="transactions")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transaction {
    /**
     * The user Primary Key.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The amount that will be transferred.
     */
    @NotNull
    private BigDecimal amount;

    /**
     * The user who is transferring the amount.
     * <p>This is a Foreign Key in Database.
     */
    @NotNull
    @ManyToOne @JoinColumn(name="user_sender_id")
    private User sender;

    /**
     * The user who is receiving the amount.
     * <p>This is a Foreign Key in Database.
     */
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
