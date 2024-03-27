package com.example.springsecurity.service.impl;

import com.example.springsecurity.entities.RefreshToken;
import com.example.springsecurity.reposiroty.RefreshTokenRepository;
import com.example.springsecurity.reposiroty.UserRepository;
import com.example.springsecurity.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    public RefreshToken findByToken(String token) {
        return null;
    }

    @Override
    public RefreshToken createRefreshToken(String email) {
        return refreshTokenRepository.save(RefreshToken.builder()
                .userInfo(userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Email not found")))
                .token(UUID.randomUUID().toString())
                .expireDate(Instant.now().plusMillis(600000))
                .build());
    }

    @Override
    public RefreshToken verifyExpiration(String token) {
        RefreshToken refreshToken = refreshTokenRepository.findByToken(token).orElseThrow(() -> new RuntimeException("Refresh token not found !"));
        if (refreshToken.getExpireDate().isBefore(Instant.now()))
            throw new RuntimeException("Refresh token has expiration ! Please login again to continue !");
        return refreshToken;
    }
}
