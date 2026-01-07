package com.desafiopicpay.service;

import com.desafiopicpay.model.dto.TransactionRequestDTO;
import com.desafiopicpay.exception.http.ServiceUnavailableException;
import com.desafiopicpay.exception.transaction.TransactionForbiddenException;

/**
 * Interface to connect external authorization service for authenticate transactions.
 * @author Filipe Martins
 */
public interface AuthorizationService {
    /**
     * Request external service to authorize a transaction.
     * @param transaction the transaction requested to authorize
     * @throws ServiceUnavailableException if the service responses is unavailable
     * @throws TransactionForbiddenException if the transaction was not accept
     */
    public void authorizeTransaction(TransactionRequestDTO transaction) throws TransactionForbiddenException, ServiceUnavailableException;
}
