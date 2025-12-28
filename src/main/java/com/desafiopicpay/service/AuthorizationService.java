package com.desafiopicpay.service;

import com.desafiopicpay.dto.TransactionRequestDTO;
import com.desafiopicpay.exception.http.InternalServerErrorException;
import com.desafiopicpay.exception.http.ServiceUnavailableException;
import com.desafiopicpay.exception.transaction.TransactionForbiddenException;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * Class to request external Transaction Authorization Service
 */
@Service
public class AuthorizationService {
    @Autowired
    private RestTemplate restTemplate;

    public void authorizeTransaction(TransactionRequestDTO transaction){
        Dotenv dotenv = Dotenv.load();
        final String URL_API_AUTH = dotenv.get("URL_API_AUTH_TRANSFER");
        if (URL_API_AUTH == null){
            throw new RuntimeException("Var env not found");
        }

        ResponseEntity<Map> response;
        try {
            response = this.restTemplate.getForEntity(URL_API_AUTH, Map.class);
        } catch (HttpClientErrorException exception){
            if (exception.getStatusCode() == HttpStatus.FORBIDDEN){
                throw new TransactionForbiddenException("Transaction Unauthorized");
            }
            else if (exception.getStatusCode() ==  HttpStatus.INTERNAL_SERVER_ERROR){
                throw new ServiceUnavailableException("Internal Service Unavailable");
            }
            else {
                throw new InternalServerErrorException("Server Unavailable");
            }
        }

        HttpStatusCode statusCode = response.getStatusCode();
        if ( statusCode != HttpStatus.OK ){
            throw new TransactionForbiddenException("Transaction Unauthorized");
        }
    }
}
