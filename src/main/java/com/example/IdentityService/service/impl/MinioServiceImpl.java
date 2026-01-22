package com.example.IdentityService.service.impl;

import com.example.IdentityService.service.MinioService;
import io.minio.*;
import io.minio.errors.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Service
@RequiredArgsConstructor
@Slf4j
public class MinioServiceImpl implements MinioService {
    private final MinioClient minioClient;

    @Override
    public void uploadFile(String bucketName, MultipartFile file) {
        try {
            boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket("bucket-test").build());
            if (!found) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket("bucket-test").build());
            } else {
                System.out.println("bucket-test already exist !");
            }
            minioClient.uploadObject(UploadObjectArgs.builder()
                    .bucket("bucket-test")
                    .object("/project284/item/test.txt")
                    .filename("C:\\Users\\ADMIN\\Documents\\spring\\src\\test\\test.txt")
                    .build());
        } catch (IOException | ErrorResponseException | InsufficientDataException | InternalException |
                 InvalidKeyException | InvalidResponseException | NoSuchAlgorithmException | ServerException |
                 XmlParserException e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public byte[] getFile(String key) {
        return new byte[0];
    }
}
