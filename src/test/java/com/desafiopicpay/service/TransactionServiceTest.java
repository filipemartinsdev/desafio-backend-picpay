package com.desafiopicpay.service;

import com.desafiopicpay.dto.TransactionRequestDTO;
import com.desafiopicpay.dto.TransactionResponseDTO;
import com.desafiopicpay.entity.Transaction;
import com.desafiopicpay.entity.User;
import com.desafiopicpay.entity.UserType;
import com.desafiopicpay.exception.transaction.TransactionForbiddenException;
import com.desafiopicpay.repository.TransactionRepository;
import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ActiveProfiles("test")
class TransactionServiceTest {
    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private UserService userService;

    @Mock
    private NotificationServiceImpl notificationService;

    @Mock
    private AuthorizationService authorizationService;

    @Autowired
    @InjectMocks
    private TransactionService transactionService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Should create transaction successfully when everything is OK")
    void createCase1() {
        User sender = new User(
                1L,
                "User",
                "sender",
                "12312312312",
                "sender@gmail.com",
                "password",
                new BigDecimal(20),
                UserType.COMMON
        );
        User receiver =  new User(
                2L,
                "User",
                "receiver",
                "45645645645",
                "receiver@gmail.com",
                "password",
                new BigDecimal(0),
                UserType.MERCHANT
        );

        Mockito.when(userService.findUserById(1L)).thenReturn(sender);
        Mockito.when(userService.findUserById(2L)).thenReturn(receiver);

        Mockito.when(transactionRepository.save(any())).thenReturn(new Transaction(1L, new BigDecimal(20), sender, receiver, LocalDateTime.now()));

        var transactionRequestDTO = new TransactionRequestDTO();
        transactionRequestDTO.setAmount(new BigDecimal(20));
        transactionRequestDTO.setSender(1L);
        transactionRequestDTO.setReceiver(2L);

        this.transactionService.create(transactionRequestDTO);
        Mockito.verify(transactionRepository, times(1)).save(any());

        sender.setBalance(new BigDecimal(0));
        receiver.setBalance(new BigDecimal(20));

        verify(userService, times(1)).save(sender);
        verify(userService, times(1)).save(receiver);

        verify(notificationService, times(1)).notifyTransaction(any());
    }

    @Test
    @DisplayName("Should not create transaction when insufficient funds")
    void createCase2() {
        User sender = new User(
                1L,
                "User",
                "sender",
                "12312312312",
                "sender@gmail.com",
                "password",
                new BigDecimal(0),
                UserType.COMMON
        );
        User receiver =  new User(
                2L,
                "User",
                "receiver",
                "45645645645",
                "receiver@gmail.com",
                "password",
                new BigDecimal(0),
                UserType.COMMON
        );

        var transactionRequest = new TransactionRequestDTO();
        transactionRequest.setAmount(new BigDecimal(1));
        transactionRequest.setSender(sender.getId());
        transactionRequest.setReceiver(receiver.getId());

        Mockito.when(userService.findUserById(sender.getId())).thenReturn(sender);
        Mockito.when(userService.findUserById(receiver.getId())).thenReturn(receiver);

        Mockito.doThrow(new TransactionForbiddenException("Transaction Unauthorized")).when(authorizationService).authorizeTransaction(transactionRequest);

        Assertions.assertThrows(TransactionForbiddenException.class, ()->{
            this.transactionService.create(transactionRequest);
        });
    }

    @Test
    @DisplayName("Should not create transaction when sender is MERCHANT")
    void createCase3() {
        User sender = new User(
                1L,
                "User",
                "sender",
                "12312312312",
                "sender@gmail.com",
                "password",
                new BigDecimal(0),
                UserType.MERCHANT
        );
        User receiver =  new User(
                2L,
                "User",
                "receiver",
                "45645645645",
                "receiver@gmail.com",
                "password",
                new BigDecimal(0),
                UserType.COMMON
        );

        var transactionRequest = new TransactionRequestDTO();
        transactionRequest.setAmount(new BigDecimal(1));
        transactionRequest.setSender(sender.getId());
        transactionRequest.setReceiver(receiver.getId());
        
        Mockito.doThrow(new TransactionForbiddenException("Transaction unauthorized")).when(authorizationService).authorizeTransaction(transactionRequest);
        
        Assertions.assertThrows(TransactionForbiddenException.class, ()->{
           transactionService.create(transactionRequest); 
        });
    }


    @Test
    @DisplayName("Should get all the transactions successfully from DB")
    void getAll() {
        User sender = new User(
                1L,
                "User",
                "sender",
                "12312312312",
                "sender@gmail.com",
                "password",
                new BigDecimal(10),
                UserType.COMMON
        );
        User receiver =  new User(
                2L,
                "User",
                "receiver",
                "45645645645",
                "receiver@gmail.com",
                "password",
                new BigDecimal(0),
                UserType.COMMON
        );

        List<Transaction> transactionList = List.of(
                new Transaction(1L, new BigDecimal(10), sender, receiver, LocalDateTime.now()),
                new Transaction(2L, new BigDecimal(10), receiver, sender, LocalDateTime.now()),
                new Transaction(3L, new BigDecimal(10), sender, receiver, LocalDateTime.now())
        );

        Mockito.when(transactionRepository.findAll()).thenReturn(transactionList);

        List<TransactionResponseDTO> expectedResponseList = transactionList.stream().map(TransactionResponseDTO::new).toList();
        List<TransactionResponseDTO> responseList = transactionService.getAll();

        Mockito.verify(transactionRepository, times(1)).findAll();
        Assertions.assertEquals(expectedResponseList, responseList);
    }

    @Test
    @DisplayName("Should get the transaction by id successfully from DB")
    void getById() {
        User sender = new User(
                1L,
                "User",
                "sender",
                "12312312312",
                "sender@gmail.com",
                "password",
                new BigDecimal(10),
                UserType.COMMON
        );
        User receiver =  new User(
                2L,
                "User",
                "receiver",
                "45645645645",
                "receiver@gmail.com",
                "password",
                new BigDecimal(0),
                UserType.COMMON
        );

        var transaction = new Transaction();
        transaction.setId(1L);
        transaction.setAmount(new BigDecimal(10));
        transaction.setSender(sender);
        transaction.setReceiver(receiver);
        transaction.setTimestamp(LocalDateTime.now());

        var transactionResponseDTOExpected = new TransactionResponseDTO(transaction);

        Mockito.when(transactionRepository.findById(1L)).thenReturn(Optional.of(transaction));

        TransactionResponseDTO transactionResponseDTO = transactionService.getById(1L);

        Mockito.verify(transactionRepository, times(1)).findById(1L);

        Assertions.assertEquals(transactionResponseDTOExpected, transactionResponseDTO);
    }
}
