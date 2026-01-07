package com.desafiopicpay.service;

import com.desafiopicpay.model.dto.TransactionRequestDTO;
import com.desafiopicpay.model.dto.TransactionResponseDTO;
import com.desafiopicpay.model.entity.UserType;
import com.desafiopicpay.exception.http.NotFoundException;
import com.desafiopicpay.model.entity.Transaction;
import com.desafiopicpay.model.entity.User;
import com.desafiopicpay.exception.transaction.TransactionForbiddenException;
import com.desafiopicpay.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * Service to manage transaction operations.
 * <p>This depends on a {@link TransactionRepository}.
 * @author Filipe Martins
 * @see Transaction
 */
@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private NotificationServiceImpl notificationService;

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

        this.validateTransaction(sender, transactionRequest.getAmount());

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

    public void validateTransaction(User sender, BigDecimal amount) throws TransactionForbiddenException, NotFoundException{
        if (sender.getUserType() == UserType.MERCHANT){
            throw new TransactionForbiddenException("User type MERCHANT is not authorized to transfer");
        }

        if (sender.getBalance().compareTo(amount) < 0){
            throw new TransactionForbiddenException("Insufficient funds");
        }
    }
}
