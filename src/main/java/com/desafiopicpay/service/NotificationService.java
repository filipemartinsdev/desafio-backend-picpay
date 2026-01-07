package com.desafiopicpay.service;

import com.desafiopicpay.model.entity.Transaction;
import com.desafiopicpay.exception.http.ServiceUnavailableException;

/**
 * Interface to connect external notification service for notify transactions to users.
 * @author Filipe Martins
 */
public interface NotificationService {
    /**
     * Request external service to notify.
     * @param transaction the transaction requested to notify
     * @throws ServiceUnavailableException if the service responses is unavailable
     */
    public void notifyTransaction(Transaction transaction) throws ServiceUnavailableException;
}
