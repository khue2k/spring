package com.example.IdentityService.controllers;

import com.example.IdentityService.dtos.ResponseDTO;
import com.example.IdentityService.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RefreshTokenController {
    private final RefreshTokenService refreshTokenService;

    @GetMapping("/refresh-token/{refreshToken}")
    public ResponseDTO refreshToken(@PathVariable(name = "refreshToken") String refreshToken) {
        return new ResponseDTO();
    }
}
