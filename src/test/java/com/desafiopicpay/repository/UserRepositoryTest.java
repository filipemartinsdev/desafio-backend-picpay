package com.desafiopicpay.repository;

import com.desafiopicpay.dto.UserRequestDTO;
import com.desafiopicpay.entity.User;
import com.desafiopicpay.entity.UserType;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
public class UserRepositoryTest {
    @Autowired
    EntityManager entityManager;

    @Autowired
    UserRepository userRepository;

    @Test
    @DisplayName("Should get user by document successfully from DB")
    void findUserByDocumentCase1(){
        String document = "12312312312";
        var userDTO = new UserRequestDTO();
        userDTO.setFirstName("Filipe");
        userDTO.setLastName("Martins");
        userDTO.setDocument(document);
        userDTO.setEmail("email@gmail.com");
        userDTO.setPassword("password");
        userDTO.setBalance(new BigDecimal(0));
        userDTO.setUserType(UserType.COMMON);

        this.createUser(userDTO);
        Optional<User> foundedUser = this.userRepository.findUserByDocument(document);

        assertTrue(foundedUser.isPresent());
    };

    @Test
    @DisplayName("Should not get user by document when it not exists")
    void findUserByDocumentCase2(){
        String document = "12312312312";
        var userDTO = new UserRequestDTO();
        userDTO.setFirstName("Filipe");
        userDTO.setLastName("Martins");
        userDTO.setDocument(document);
        userDTO.setEmail("email@gmail.com");
        userDTO.setPassword("password");
        userDTO.setBalance(new BigDecimal(0));
        userDTO.setUserType(UserType.COMMON);

        Optional<User> foundedUser = this.userRepository.findUserByDocument("11111111111");

        assertFalse(foundedUser.isPresent());
    };

    @Test
    @DisplayName("Should get user by email successfully from DB")
    void findUserByEmailCase1(){
        String email = "email@gmail.com";
        var userDTO = new UserRequestDTO();
        userDTO.setFirstName("Filipe");
        userDTO.setLastName("Martins");
        userDTO.setDocument("12312312312");
        userDTO.setEmail(email);
        userDTO.setPassword("password");
        userDTO.setBalance(new BigDecimal(0));
        userDTO.setUserType(UserType.COMMON);

        this.createUser(userDTO);
        Optional<User> foundedUser = this.userRepository.findUserByEmail(email);
        assertTrue(foundedUser.isPresent());
    };

    @Test
    @DisplayName("Should not get user by email when it not exists")
    void findUserByEmailCase2(){
        String email = "email@gmail.com";
        var userDTO = new UserRequestDTO();
        userDTO.setFirstName("Filipe");
        userDTO.setLastName("Martins");
        userDTO.setDocument("12312312312");
        userDTO.setEmail(email);
        userDTO.setPassword("password");
        userDTO.setBalance(new BigDecimal(0));
        userDTO.setUserType(UserType.COMMON);

        Optional<User> foundedUser = this.userRepository.findUserByEmail("wrongemail@gmail.com");
        assertFalse(foundedUser.isPresent());
    };

    private User createUser(UserRequestDTO data){
        User user = new User(data);
        this.entityManager.persist(user);
        return user;
    }
}
