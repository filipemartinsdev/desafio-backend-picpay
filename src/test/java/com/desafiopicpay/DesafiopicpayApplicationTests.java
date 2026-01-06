package com.desafiopicpay;

import com.desafiopicpay.controller.TransactionController;
import com.desafiopicpay.controller.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest
class DesafiopicpayApplicationTests {

    @Autowired
    private UserController userController;

    @Autowired
    private TransactionController transactionController;

	@Test
	void contextLoads() {
        assertThat(userController).isNotNull();
        assertThat(transactionController).isNotNull();
	}

}
