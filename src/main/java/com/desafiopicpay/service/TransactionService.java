package com.desafiopicpay.service;

import com.desafiopicpay.dto.TransactionRequest;
import com.desafiopicpay.dto.TransactionResponse;
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

    public List<TransactionResponse> getAll(){
        return this.transactionRepository.findAll().stream()
                .map(TransactionResponse::new)
                .toList();
    }

    public TransactionResponse getById(Long id){
        return new TransactionResponse(
                this.transactionRepository.findById(id).orElseThrow(()->{
                    return new NotFoundException("Transaction not found");
                })
        );
    }

    public TransactionResponse create(TransactionRequest transactionRequest){
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

        return new TransactionResponse(
                this.transactionRepository.save(transaction)
        );
    }
}
