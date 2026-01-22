package com.example.IdentityService.service;

import com.example.IdentityService.dtos.JwtResponseDTO;
import com.example.IdentityService.dtos.UserDTO;
import com.example.IdentityService.entities.User;

public interface UserService {
    User saveUser(UserDTO userDTO);

    String auth(UserDTO userDTO);

    User userInfo(String token);

    boolean verifyToken(String token);

    void forgotPassword(String userDTO);

    void changePassword(String token, UserDTO userDTO);

    boolean logOut(String jwtToken);

    JwtResponseDTO refreshToken(String refreshToken);
}
