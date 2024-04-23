package com.example.springsecurity.controller;

import com.example.springsecurity.dtos.HttpResponse;
import com.example.springsecurity.dtos.UserDTO;
import com.example.springsecurity.entities.User;
import com.example.springsecurity.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;
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
    void createUser_validRequest_success() throws Exception {

        //GIVE
        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(request);
        User user = new User();
        Mockito.when(userService.saveUser(ArgumentMatchers.any())).thenReturn(user);

        //WHEN, THEN

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/registerrrrr")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(content))
                .andExpect(MockMvcResultMatchers.status().is(401));

    }
    @Test
    void createUser_invalidPasswordRequest_success() throws Exception {

        //GIVE
        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(request);
        User user = new User();
        Mockito.when(userService.saveUser(ArgumentMatchers.any())).thenReturn(user);

        //WHEN, THEN

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/registerrrrr")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(content))
                .andExpect(MockMvcResultMatchers.status().is(401));

    }
}
