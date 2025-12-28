package com.desafiopicpay.service;

import com.desafiopicpay.dto.TransactionRequestDTO;
import com.desafiopicpay.dto.TransactionResponseDTO;
import com.desafiopicpay.exception.http.NotFoundException;
import com.desafiopicpay.entity.Transaction;
import com.desafiopicpay.entity.User;
import com.desafiopicpay.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private AuthorizationService authorizationService;

    public List<TransactionResponseDTO> getAll(){
        return this.transactionRepository.findAll().stream()
                .map(TransactionResponseDTO::new)
                .toList();
    }

    public TransactionResponseDTO getById(Long id){
        return new TransactionResponseDTO(
                this.transactionRepository.findById(id).orElseThrow(()->{
                    return new NotFoundException("Transaction not found");
                })
        );
    }

    public TransactionResponseDTO create(TransactionRequestDTO transactionRequest){
        User sender = this.userService.findUserById(transactionRequest.getSender());
        User receiver = this.userService.findUserById(transactionRequest.getReceiver());

        this.userService.validateTransaction(sender, transactionRequest.getAmount());

        this.authorizationService.authorizeTransaction(transactionRequest);

        sender.setBalance(sender.getBalance().subtract(transactionRequest.getAmount()));
        receiver.setBalance(receiver.getBalance().add(transactionRequest.getAmount()));

        this.userService.save(sender);
        this.userService.save(receiver);

        Transaction transaction = new Transaction(sender, receiver, transactionRequest.getAmount());

        this.notificationService.notifyTransaction(transaction);

        return new TransactionResponseDTO(
                this.transactionRepository.save(transaction)
        );
    }
}
