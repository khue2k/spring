package com.example.springsecurity.controllers;

import com.example.springsecurity.dto.ResponseDTO;
import com.example.springsecurity.service.MinioService;
import com.example.springsecurity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class HomeController {
    private final UserService userService;
    private final MinioService minioService;

    @GetMapping("/admin")
    public ResponseEntity<?> admin() {
        return ResponseEntity.ok(new ResponseDTO<>("TEST ADMIN", 200));
    }

    @GetMapping("/user")
    public ResponseEntity<?> user() {
        return ResponseEntity.ok(new ResponseDTO<>("TEST USER", 200));
    }

    @GetMapping("/user-info")
    public ResponseEntity<?> userInfo(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(new ResponseDTO<>("Success", 200, userService.userInfo(token)));
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping("/read")
    public ResponseEntity<?> read() {
        return ResponseEntity.ok(new ResponseDTO<>("CAN READ", 200));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/edit")
    public ResponseEntity<?> edit() {
        return ResponseEntity.ok(new ResponseDTO<>("CAN EDIT", 200));
    }

    @GetMapping("/public")
    public ResponseEntity<?> publicApi() {
        return ResponseEntity.ok(new ResponseDTO<>("Hello Khue1234 ", 200));
    }

    @PostMapping("/public/upload-file")
    public ResponseEntity<?> uploadFile(@RequestParam(name = "file") MultipartFile file) {
        minioService.uploadFile("tenant-ptit", file);
        return ResponseEntity.ok(new ResponseDTO<>("success", 200));
    }
}
