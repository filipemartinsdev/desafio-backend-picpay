package com.desafiopicpay.service;

import com.desafiopicpay.entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Class to request external Notification Service
 */
@Service
public class NotificationService {
    @Autowired
    private RestTemplate restTemplate;

    public void notifyTransaction(Transaction transaction){
//        String email = user.getEmail();

    }
}
