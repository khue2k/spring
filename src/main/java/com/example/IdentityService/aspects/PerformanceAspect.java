package com.example.IdentityService.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class PerformanceAspect {
//    @Pointcut("within(com.example.springsecurity.controllers.*)")
//    public void controllerMethods() {
//
//    }
//
//    @Before("controllerMethods()")
//    public void beforeMethodExecution(JoinPoint joinPoint) {
//        String methodName = joinPoint.getSignature().getName();
//        log.info("Starting execution of " + methodName);
//    }
//
//    @After("controllerMethods()")
//    public void afterMethodExecution(JoinPoint joinPoint) {
//        String methodName = joinPoint.getSignature().getName();
//        log.info("Finished execution of " + methodName);
//    }
//
//    @Around("controllerMethods()")
//    public void calculatingTimeToSolveRequest(ProceedingJoinPoint joinPoint) throws Throwable {
//        long startTime = System.nanoTime();
//        joinPoint.proceed();
//        long endTime = System.nanoTime();
//        log.info("Time to solve request : " + (endTime - startTime));
//    }
}
