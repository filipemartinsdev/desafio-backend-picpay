package com.desafiopicpay.service;

import com.desafiopicpay.dto.PagedUsersResponseDTO;
import com.desafiopicpay.dto.UserRequestDTO;
import com.desafiopicpay.dto.UserResponseDTO;
import com.desafiopicpay.exception.http.NotFoundException;
import com.desafiopicpay.exception.transaction.TransactionForbiddenException;
import com.desafiopicpay.entity.User;
import com.desafiopicpay.entity.UserType;
import com.desafiopicpay.mapper.UserMapper;
import com.desafiopicpay.repository.TransactionRepository;
import com.desafiopicpay.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * Service to manage user operations.
 * <p>This depends on a {@link UserRepository}.
 * @author Filipe Martins
 * @see User
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public PagedUsersResponseDTO getAll(Pageable pageable){
        Page<User> page = this.userRepository.findAll(pageable);
        PagedUsersResponseDTO pagedUsers =  new PagedUsersResponseDTO(page);
        return pagedUsers;
    }

    public UserResponseDTO getByDocument(String document){
        return UserMapper.toUserResponse(
                this.userRepository.findUserByDocument(document).orElseThrow(()->{
                    return new NotFoundException("User not found");
                })
        );
    }

    public UserResponseDTO getById(Long id){
        return UserMapper.toUserResponse(
                this.userRepository.findById(id).orElseThrow(()->{
                    return new NotFoundException("User not found");
                })
        );
    }

    public UserResponseDTO save(User user){
        return UserMapper.toUserResponse(
                this.userRepository.save(user)
        );
    }

    public UserResponseDTO replace(Long id, UserRequestDTO userRequest){
        if (!this.existsById(id)){
            throw new NotFoundException("User not exists");
        }

        User user = new User(userRequest);
        user.setId(id);

        return UserMapper.toUserResponse(
                this.userRepository.save(user)
        );
    }

    public boolean existsById(Long id){
        return this.userRepository.existsById(id);
    }

    public void delete(Long id){
        if (!this.existsById(id)){
            throw new NotFoundException("User not exists");
        }
        this.userRepository.deleteById(id);
    }



    /**
     * Restrict method for use in TransactionService.
     * <p>Using this approach to TransactionService not know UserRepository directly.
     * @param id the user id
     * @return the requested user
     */
    public User findUserById(Long id){
        return this.userRepository.findById(id).orElseThrow(()->{
            return new NotFoundException("User not found");
        });
    }
}
