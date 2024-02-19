package com.example.springsecurity.controllers;

import com.example.springsecurity.dtos.ResponseDTO;
import com.example.springsecurity.service.UserService;
import com.example.springsecurity.test.redis.Student;
import com.example.springsecurity.test.redis.StudentRepository;
import com.example.springsecurity.test.redis.StudentService;
import io.minio.*;
import io.minio.errors.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class HomeController {
    private final UserService userService;

    private final MinioClient minioClient;

    private final Environment environment;

    private final StudentService studentService;
    private final StudentRepository studentRepository;

    private final MessageSource messageSource;

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
        try (InputStream inputStream = minioClient.getObject(GetObjectArgs.builder().bucket("bucket-test").object("file/abc.txt").build())) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            Stream<String> stream;
            stream = bufferedReader.lines();
            stream.forEach(System.out::println);
        } catch (Exception e) {
        }

        return null;
    }

    @PostMapping("/public/upload-file")
    public ResponseEntity<?> uploadFile(@RequestParam(name = "file") MultipartFile file) throws Exception {
        throw new Exception("khuee");
    }

    @GetMapping("/public/test-load-balancing")
    public ResponseEntity<?> testLoadBalancing() {
        String variable = environment.getProperty("variable-environment");
        log.info(variable);
        log.info("----------Receive request success----------");
        return ResponseEntity.ok(new ResponseDTO<>("OK", 200));
    }

    @GetMapping("public/test")
    public ResponseEntity<?> testPublicApi() {
        return ResponseEntity.ok(new ResponseDTO<>("OK", 200));
    }
}
