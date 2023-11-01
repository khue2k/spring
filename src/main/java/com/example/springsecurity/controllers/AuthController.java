package com.example.springsecurity.controllers;

import com.example.springsecurity.dto.HttpResponse;
import com.example.springsecurity.dto.ResponseDTO;
import com.example.springsecurity.dto.UserDTO;
import com.example.springsecurity.entities.User;
import com.example.springsecurity.service.UserService;
import com.example.springsecurity.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<HttpResponse> register(@RequestBody UserDTO userDTO) {
        User user = userService.saveUser(userDTO);
        return ResponseEntity.created(URI.create("")).body(
                HttpResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .message("User created")
                        .status(HttpStatus.CREATED)
                        .statusCode(HttpStatus.CREATED.value())
                        .data(Map.of("user", user))
                        .build()
        );
    }

    @PostMapping("/confirm")
    public ResponseEntity<HttpResponse> confirm(@RequestParam("token") String token) {
        boolean isSuccess = userService.verifyToken(token);
        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .message("Account verified")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .data(Map.of("Success", isSuccess))
                        .build()
        );
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(new ResponseDTO<>(userService.auth(userDTO), 200));
    }

    @PostMapping("/auth/refresh-token")
    public ResponseEntity<?> refreshToken() {
        return ResponseEntity.ok(new ResponseDTO<>("Test OK", 200));
    }

}
