package com.example.springsecurity.controllers;

import com.example.springsecurity.dtos.HttpResponse;
import com.example.springsecurity.dtos.JwtResponseDTO;
import com.example.springsecurity.dtos.ResponseDTO;
import com.example.springsecurity.dtos.UserDTO;
import com.example.springsecurity.entities.User;
import com.example.springsecurity.service.RefreshTokenService;
import com.example.springsecurity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
//com.example.springsecurity.controllers.AuthController
public class AuthController {
    private final UserService userService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/register")
    public ResponseEntity<HttpResponse> register(@RequestBody UserDTO userDTO) {
        try {
            User user = userService.saveUser(userDTO);
            Map<String, Object> map = new HashMap<>();
            map.put("user", user);
            return ResponseEntity.created(URI.create("")).body(
                    HttpResponse.builder()
                            .timeStamp(LocalDateTime.now().toString())
                            .message("User created")
                            .status(HttpStatus.CREATED)
                            .statusCode(HttpStatus.CREATED.value())
                            .data(map)
                            .build()
            );
        } catch (Exception e) {
            Map<String, Object> map = new HashMap<>();
            map.put("user", "ERROR");
            return ResponseEntity.created(URI.create("")).body(
                    HttpResponse.builder()
                            .timeStamp(LocalDateTime.now().toString())
                            .message("User invalid")
                            .status(HttpStatus.BAD_REQUEST)
                            .statusCode(HttpStatus.BAD_REQUEST.value())
                            .data(map)
                            .build()
            );
        }
    }

    @GetMapping("/confirm")
    public ResponseEntity<HttpResponse> confirm(@RequestParam("token") String token) {
        boolean isSuccess = userService.verifyToken(token);
        Map<String, Object> map = new HashMap<>();
        map.put("Result : ", isSuccess);
        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .message("Account verified")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .data(map)
                        .build()
        );
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO) {
        String jwt = userService.auth(userDTO);
        String refreshToken = refreshTokenService.createRefreshToken(userDTO.getEmail()).getToken();
        JwtResponseDTO jwtResponseDTO = new JwtResponseDTO(jwt, refreshToken);
        return ResponseEntity.ok(new ResponseDTO<>("OK", 200, jwtResponseDTO));
    }

    @PostMapping("/sign-out")
    public ResponseEntity<?> signOut() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!Objects.equals(principal.toString(), "anonymousUser")) {

        }
        return null;
    }

    @PostMapping("/auth/refresh-token")
    public ResponseEntity<?> refreshToken() {

        return ResponseEntity.ok(new ResponseDTO<>("Test OK", 200));
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody String email) {
        userService.forgotPassword(email);
        return ResponseEntity.ok(new ResponseDTO<>("Successful", 200));
    }

    @PostMapping("/confirm-reset-password")
    public ResponseEntity<?> changePassword(@RequestBody UserDTO userDTO, @RequestParam(name = "token") String token) {
        userService.changePassword(token, userDTO);
        return ResponseEntity.ok(new ResponseDTO<>("Change password successful", 200));
    }
}
