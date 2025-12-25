package com.desafiopicpay;

import com.desafiopicpay.exception.transaction.TransactionForbiddenException;
import com.desafiopicpay.entity.User;
import com.desafiopicpay.service.UserService;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTests {

    @Test
    public void testValidateTransactionInsufficientBalance1(){
        UserService userService = new UserService();
        User user = User.builder()
                .balance(BigDecimal.valueOf(10))
                .build();
        var amount = BigDecimal.valueOf(11);
        assertThrows(TransactionForbiddenException.class , ()->userService.validateTransaction(user, amount));
    }
}
