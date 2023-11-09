package com.example.springsecurity.controllers;

import com.example.springsecurity.dto.ResponseDTO;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.UploadObjectArgs;
import io.minio.errors.MinioException;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("api")
public class UploadFileController implements ApplicationRunner {
    @PostMapping("/upload-file")
    public ResponseEntity<?> uploadFile(@RequestParam(name = "file") MultipartFile file) {

        try {
            InputStream inputStream = file.getInputStream();
            MinioClient minioClient = MinioClient.builder().endpoint("localhost:9000")
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
                    .filename("C:\\Users\\songk\\Documents\\spring\\spring\\src\\test\\test.txt")
                    .build());
            return ResponseEntity.ok(new ResponseDTO<>("upload file success", 200));
        } catch (MinioException | IOException | InvalidKeyException | NoSuchAlgorithmException e) {
            return ResponseEntity.ok(new ResponseDTO<>("upload file failed", 200));
        }
    }

    @Override
    public void run(ApplicationArguments args) {
        try {
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
                    .filename("C:\\Users\\songk\\Documents\\spring\\spring\\src\\test\\test.txt")
                    .build());
            System.out.println("upload file success");
        } catch (MinioException | IOException | InvalidKeyException | NoSuchAlgorithmException ignored) {
        }
    }
}
