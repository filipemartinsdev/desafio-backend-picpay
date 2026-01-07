package com.desafiopicpay.service;

import com.desafiopicpay.model.entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class NotificationServiceImpl implements NotificationService{
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void notifyTransaction(Transaction transaction){
        // mocked endpoint is broken
    }
}
