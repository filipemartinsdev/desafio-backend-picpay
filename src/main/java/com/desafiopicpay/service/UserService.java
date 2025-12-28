package com.desafiopicpay.service;

import com.desafiopicpay.dto.UserRequestDTO;
import com.desafiopicpay.dto.UserResponseDTO;
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

    public List<UserResponseDTO> getAll(){
        return this.userRepository.findAll().stream()
                .map(UserResponseDTO::new).toList();
    }

    public UserResponseDTO getByDocument(String document){
        return new UserResponseDTO(
                this.userRepository.findUserByDocument(document).orElseThrow(()->{
                    return new NotFoundException("User not found");
                })
        );
    }

    public UserResponseDTO getById(Long id){
        return new UserResponseDTO(
                this.userRepository.findById(id).orElseThrow(()->{
                    return new NotFoundException("User not found");
                })
        );
    }

    public UserResponseDTO save(User user){
        return new UserResponseDTO(
                this.userRepository.save(user)
        );
    }

    public UserResponseDTO replace(Long id, UserRequestDTO userRequest){
        if (!this.existsById(id)){
            throw new NotFoundException("User not exists");
        }

        User user = new User(userRequest);
        user.setId(id);

        return new UserResponseDTO(
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
