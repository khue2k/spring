package com.example.springsecurity.test.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RedisTest {
    @Autowired
    private StudentService studentService;


    public static void main(String[] args) {

    }
}
