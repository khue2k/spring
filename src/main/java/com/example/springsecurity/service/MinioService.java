package com.example.springsecurity.service;

import org.springframework.web.multipart.MultipartFile;

public interface MinioService {
    void uploadFile(String bucketName, MultipartFile file);

    byte[] getFile(String key);
}
