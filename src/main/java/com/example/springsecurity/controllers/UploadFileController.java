package com.example.springsecurity.controllers;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.UploadObjectArgs;
import io.minio.errors.MinioException;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("api")
public class UploadFileController implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) {
        try {
            long startTime=System.currentTimeMillis();
            MinioClient minioClient = MinioClient.builder().endpoint("http://127.0.0.1:9000")
                    .credentials("khue1234", "khue1234").build();
            boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket("bucket-test").build());
            if (!found) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket("bucket-test").build());
            } else {
                System.out.println("bucket-test already exist !");
            }
            minioClient.uploadObject(UploadObjectArgs.builder()
                    .bucket("bucket-test")
                    .object("file-test-123")
                    .filename("C:\\Users\\ADMIN\\Documents\\spring\\src\\test\\test.txt")
                    .build());
            long endTime=System.currentTimeMillis();
            System.out.println("upload file success take : "+(endTime-startTime)+" ms");
        } catch (MinioException | IOException | InvalidKeyException | NoSuchAlgorithmException ignored) {
        }
    }
}
