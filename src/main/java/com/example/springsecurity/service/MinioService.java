package com.example.springsecurity.service;

public interface MinioService {
    void uploadFile(String name, byte[] content);
    byte[] getFile(String key);
}
