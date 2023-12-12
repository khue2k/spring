package com.example.springsecurity.test;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class TestServiceAspect {
    @Before(value = "execution(* com.example.springsecurity.test.redis.StudentService.*(..))")
    public void before(JoinPoint joinPoint) {
        System.out.println("Before method:" + joinPoint.getSignature());
        log.info("-------------Khue1234-------------");
    }
    @After(value = "execution(* com.example.springsecurity.test.redis.StudentService.*(..))")
    public void after(JoinPoint joinPoint) {
        System.out.println("after method:" + joinPoint.getSignature());
        log.info("-------------Khue1234-------------");
    }
}
