package com.example.IdentityService.service;

import com.example.IdentityService.dtos.HttpResponse;
import com.example.IdentityService.dtos.UserDTO;
import com.example.IdentityService.reposiroty.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    private HttpResponse httpResponse;
    private UserDTO request;

    @BeforeEach
    private void initData() {
        this.request = UserDTO.builder()
                .email("songkhecole@gmail.com")
                .firstName("Duong")
                .lastName("Khue")
                .password("Khue1234")
                .build();
        httpResponse = HttpResponse.builder().status(HttpStatus.OK).build();
    }

    @Test
    void createUser_success() {

        //GIVE

        //WHEN

        //THEN

    }
}
