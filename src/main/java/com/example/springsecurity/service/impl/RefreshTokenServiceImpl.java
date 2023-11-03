package com.example.springsecurity.service.impl;

import com.example.springsecurity.entities.RefreshToken;
import com.example.springsecurity.service.RefreshTokenService;

public class RefreshTokenServiceImpl implements RefreshTokenService {

    @Override
    public RefreshToken findByToken(String token) {
        return null;
    }

    @Override
    public RefreshToken createRefreshToken() {
        return null;
    }

    @Override
    public void verifyExpiration(String refreshToken) {

    }
}
