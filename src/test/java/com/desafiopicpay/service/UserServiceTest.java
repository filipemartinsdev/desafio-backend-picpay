package com.desafiopicpay.service;

import com.desafiopicpay.model.dto.PagedUsersResponseDTO;
import com.desafiopicpay.model.dto.UserRequestDTO;
import com.desafiopicpay.model.dto.UserResponseDTO;
import com.desafiopicpay.model.entity.User;
import com.desafiopicpay.model.entity.UserType;
import com.desafiopicpay.exception.http.NotFoundException;
import com.desafiopicpay.mapper.UserMapper;
import com.desafiopicpay.repository.UserRepository;

import jakarta.persistence.EntityManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

@ActiveProfiles("test")
class UserServiceTest {

    @Autowired
    private EntityManager entityManager;

    @Mock
    private UserRepository userRepository;

    @Autowired
    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(this);
    }

    private final User userCommonMock = new User(
            1L,
            "User name test1",
            "User lastname test1",
            "12312312312",
            "usertest1@gmail.com",
            "password",
            new BigDecimal(0),
            UserType.COMMON
    );

    private final User userMerchantMock = new User(
            2L,
            "User name test2",
            "User lastname test2",
            "45645645656",
            "usertest2@gmail.com",
            "password",
            new BigDecimal(0),
            UserType.MERCHANT
    );

    @Test @DisplayName("Should get all users registered on DB")
    void getAllCase1() {
        List<User> userList = new ArrayList<>(List.of(
                this.userCommonMock,
                this.userMerchantMock
        ));

        List<UserResponseDTO> spectedUserList = new ArrayList<>(List.of(
                UserMapper.toUserResponse(this.userCommonMock),
                UserMapper.toUserResponse(this.userMerchantMock)
        ));

        Mockito.when(this.userRepository.findAll(any(Pageable.class))).thenReturn(
                new PageImpl<User>(List.of(this.userCommonMock, this.userMerchantMock))
        );

        PagedUsersResponseDTO responsePage = this.userService.getAll(PageRequest.of(0,10));
        List<UserResponseDTO> responseList = responsePage.getContent();
       assertEquals(spectedUserList, responseList);
    }

    @Test @DisplayName("Should get an empty List when no users are found users in the DB")
    void getAllCase2(){
        List<User> emptyUserList = new ArrayList<>();
        Mockito.when(this.userRepository.findAll(any(Pageable.class))).thenReturn(
                Page.empty()
        );

        PagedUsersResponseDTO responseList = this.userService.getAll(PageRequest.of(0, 10));

        assertThat(responseList.getContent()).isEmpty();
    }

    @Test @DisplayName("Should get the User by Id successfully from DB")
    void getByIdCase1() {
        Mockito.when(this.userRepository.findById(this.userCommonMock.getId())).thenReturn(Optional.of(this.userCommonMock));
        UserResponseDTO spectedResponse = UserMapper.toUserResponse(this.userCommonMock);

        UserResponseDTO userResponse = this.userService.getById(1L);
        assertEquals(spectedResponse, userResponse);
    }

    @Test @DisplayName("Should throw a NotFoundException if a User is not found by Id in the DB")
    public void getByIdCase2(){
        Long id = 1L;

        Mockito.when(this.userRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, ()->{
            this.userService.findUserById(id);
        });
    }

    @Test @DisplayName("Should get the User by Document successfully from DB")
    void getByDocumentCase1() {
        String document = this.userCommonMock.getDocument();

        Mockito.when(this.userRepository.findUserByDocument(document)).thenReturn(Optional.of(this.userCommonMock));
        UserResponseDTO spectedResponse = UserMapper.toUserResponse(this.userCommonMock);

        UserResponseDTO userResponse = this.userService.getByDocument(document);
        assertEquals(spectedResponse, userResponse);
    }

    @Test @DisplayName("Should throw a NotFoundException if a User is not found by Document in the DB")
    public void getByDocumentCase2(){
        String document = "12312312312";

        Mockito.when(this.userRepository.findUserByDocument(document)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, ()->{
            this.userService.getByDocument(document);
        });
    }

    @Test @DisplayName("Should save the user successfully in the DB")
    void saveCase1() {
        Mockito.when(this.userRepository.save(this.userCommonMock)).thenReturn(this.userCommonMock);
        this.userService.save(this.userCommonMock);
        Mockito.verify(this.userRepository, Mockito.times(1)).save(this.userCommonMock);
    }

     // TODO: VALIDATE THIS TEST
//    @Test @DisplayName("Should throw a BadRequestException when the User is invalid")
//    void saveCase3() {
//        User invalidUser = new User();
//
//        assertThrows(BadRequestException.class, ()->{
//            this.userService.save(invalidUser);
//        });
//    }

    @Test @DisplayName("Should replace the user successfully in the DB")
    void replace() {
        Mockito.when(this.userRepository.save(any())).thenReturn(this.userCommonMock);
        Mockito.when(this.userRepository.existsById(this.userCommonMock.getId())).thenReturn(true);

        UserRequestDTO userRequest = UserMapper.toUserRequest(this.userCommonMock);

        new UserRequestDTO();
        userRequest.setFirstName(this.userCommonMock.getFirstName());
        userRequest.setLastName(this.userCommonMock.getLastName());
        userRequest.setBalance(this.userCommonMock.getBalance());
        userRequest.setDocument(this.userCommonMock.getDocument());
        userRequest.setEmail(this.userCommonMock.getEmail());
        userRequest.setPassword(this.userCommonMock.getPassword());
        userRequest.setUserType(this.userCommonMock.getUserType());

        this.userService.replace(this.userCommonMock.getId(), userRequest);

        Mockito.verify(this.userRepository, Mockito.times(1)).save(any());
    }

    @Test @DisplayName("Should not replace the user if it not exists")
    void replaceCase2() {
        Mockito.when(this.userRepository.existsById(this.userCommonMock.getId())).thenReturn(false);

        UserRequestDTO userRequest = new UserRequestDTO();
        userRequest.setFirstName(this.userCommonMock.getFirstName());
        userRequest.setLastName(this.userCommonMock.getLastName());
        userRequest.setBalance(this.userCommonMock.getBalance());
        userRequest.setDocument(this.userCommonMock.getDocument());
        userRequest.setEmail(this.userCommonMock.getEmail());
        userRequest.setPassword(this.userCommonMock.getPassword());
        userRequest.setUserType(this.userCommonMock.getUserType());

        assertThrows(NotFoundException.class, ()->{
            this.userService.replace(this.userCommonMock.getId(), userRequest);
        });
    }

    @Test @DisplayName("Should return false if the user not exists")
    void existsByIdCase1() {
        Mockito.when(this.userRepository.existsById(any())).thenReturn(false);
        assertFalse(this.userService.existsById(1L));
    }

    @Test @DisplayName("Should return true if the user exists")
    void existsByIdCase2() {
        Mockito.when(this.userRepository.existsById(any())).thenReturn(true);
        assertTrue(this.userService.existsById(1L));
    }

    @Test @DisplayName("Should delete successfully the user from DB")
    void deleteCase1() {
        Mockito.when(this.userRepository.existsById(this.userCommonMock.getId())).thenReturn(true);
        this.userService.delete(this.userCommonMock.getId());
        Mockito.verify(this.userRepository, Mockito.times(1)).deleteById(this.userCommonMock.getId());
    }

    @Test @DisplayName("Should throw NotFoundException if the user not exists")
    void deleteCase2() {
        Mockito.when(this.userRepository.existsById(this.userCommonMock.getId())).thenReturn(false);

        assertThrows(NotFoundException.class, ()->{
            this.userService.delete(this.userCommonMock.getId());
        });
    }

    @Test @DisplayName("Should get User by Id successfully if it exists")
    void findUserByIdCase1() {
        Mockito.when(this.userRepository.existsById(this.userCommonMock.getId())).thenReturn(true);
        Mockito.when(this.userRepository.findById(any())).thenReturn(Optional.of(this.userCommonMock));

        UserResponseDTO spectedUser = UserMapper.toUserResponse(this.userCommonMock);
        UserResponseDTO responseUser = this.userService.getById(this.userCommonMock.getId());

        assertEquals(spectedUser, responseUser);
    }

    @Test @DisplayName("Should throw NotFoundException if user not exists")
    void findUserByIdCase2() {
        Mockito.when(this.userRepository.existsById(any())).thenReturn(false);
        Mockito.when(this.userRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, ()->{
            this.userService.getById(this.userCommonMock.getId());
        });
    }

    private User createUser(User user){
        this.entityManager.persist(user);
        return user;
    }
}