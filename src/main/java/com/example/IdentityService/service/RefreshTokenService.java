package com.example.IdentityService.service;

import com.example.IdentityService.entities.RefreshToken;
import org.springframework.stereotype.Service;

@Service
public interface RefreshTokenService {

    RefreshToken findByToken(String token);

    RefreshToken createRefreshToken(String username);

    boolean verifyExpiration(String refreshToken);

    RefreshToken findValidByToken(String token);
}
