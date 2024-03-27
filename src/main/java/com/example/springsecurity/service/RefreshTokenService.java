package com.example.springsecurity.service;

import com.example.springsecurity.entities.RefreshToken;
import org.springframework.stereotype.Service;

@Service
public interface RefreshTokenService {

    RefreshToken findByToken(String token);

    RefreshToken createRefreshToken(String username);

    RefreshToken verifyExpiration(String refreshToken);
}
