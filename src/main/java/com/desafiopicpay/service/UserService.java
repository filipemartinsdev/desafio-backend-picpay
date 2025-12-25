package com.desafiopicpay.service;

import com.desafiopicpay.dto.UserRequest;
import com.desafiopicpay.dto.UserResponse;
import com.desafiopicpay.exception.http.NotFoundException;
import com.desafiopicpay.exception.transaction.TransactionForbiddenException;
import com.desafiopicpay.entity.User;
import com.desafiopicpay.entity.UserType;
import com.desafiopicpay.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<UserResponse> getAll(){
        return this.userRepository.findAll().stream()
                .map(UserResponse::new).toList();
    }

    public UserResponse getByDocument(String document){
        return new UserResponse(
                this.userRepository.findUserByDocument(document).orElseThrow(()->{
                    return new NotFoundException("User not found");
                })
        );
    }

    public UserResponse getById(Long id){
        return new UserResponse(
                this.userRepository.findById(id).orElseThrow(()->{
                    return new NotFoundException("User not found");
                })
        );
    }

    public UserResponse save(User user){
        return new UserResponse(
                this.userRepository.save(user)
        );
    }

    public UserResponse replace(Long id, UserRequest userRequest){
        if (!this.existsById(id)){
            throw new NotFoundException("User not exists");
        }

        User user = new User(userRequest);
        user.setId(id);

        return new UserResponse(
                this.userRepository.save(user)
        );
    }

    public boolean existsById(Long id){
        return this.userRepository.existsById(id);
    }

    public void delete(Long id){
        try {
            this.userRepository.deleteById(id);
        } catch (RuntimeException exception){
            throw new NotFoundException("User not exists");
        }
    }

    public void validateTransaction(User sender, BigDecimal amount) throws TransactionForbiddenException, NotFoundException{
        if (sender.getUserType() == UserType.MERCHANT){
            throw new TransactionForbiddenException("User type MERCHANT is not authorized to transfer");
        }

        if (sender.getBalance().compareTo(amount) < 0){
            throw new TransactionForbiddenException("Insufficient funds");
        }
    }

    public User findUserById(Long id){
        return this.userRepository.findById(id).orElseThrow(()->{
            return new NotFoundException("User not found");
        });
    }
}
