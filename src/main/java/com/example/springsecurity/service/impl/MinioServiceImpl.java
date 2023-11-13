package com.example.springsecurity.service.impl;

import com.example.springsecurity.service.MinioService;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Service
@RequiredArgsConstructor
@Slf4j
public class MinioServiceImpl implements MinioService {
    private final MinioClient minioClient;


    @Override
    public void uploadFile(String name, byte[] content) {
        File file = new File("/project_254/" + name);
        file.canRead();
        file.canWrite();
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(content);
            minioClient.putObject(PutObjectArgs.builder().bucket("bucket-khue").build());
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
