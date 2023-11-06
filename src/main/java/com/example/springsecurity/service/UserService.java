package com.example.springsecurity.service;

import com.example.springsecurity.dto.UserDTO;
import com.example.springsecurity.entities.User;

public interface UserService {
    User saveUser(UserDTO userDTO);

    String auth(UserDTO userDTO);

    User userInfo(String token);

    boolean verifyToken(String token);

    void forgotPassword(UserDTO userDTO);

    void changePassword(String token);
}
