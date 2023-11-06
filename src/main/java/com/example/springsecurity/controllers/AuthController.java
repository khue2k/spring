package com.example.springsecurity.controllers;

import com.example.springsecurity.dto.HttpResponse;
import com.example.springsecurity.dto.ResponseDTO;
import com.example.springsecurity.dto.UserDTO;
import com.example.springsecurity.entities.User;
import com.example.springsecurity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<HttpResponse> register(@RequestBody UserDTO userDTO) {
        try {
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
        } catch (Exception e) {
            return ResponseEntity.created(URI.create("")).body(
                    HttpResponse.builder()
                            .timeStamp(LocalDateTime.now().toString())
                            .message("User invalid")
                            .status(HttpStatus.BAD_REQUEST)
                            .statusCode(HttpStatus.BAD_REQUEST.value())
                            .data(Map.of("user", "ERROR"))
                            .build()
            );
        }
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

    @PostMapping("/signout")
    public ResponseEntity<?> signout() {
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
    public ResponseEntity<?> forgotPassword(@RequestBody UserDTO userDTO) {
        userService.forgotPassword(userDTO);
        return ResponseEntity.ok(new ResponseDTO<>("Successful", 200));
    }

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody String token) {
        userService.changePassword(token);
        return ResponseEntity.ok(new ResponseDTO<>("Change password successful", 200));
    }
}
