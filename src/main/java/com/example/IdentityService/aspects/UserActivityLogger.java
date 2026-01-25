package com.example.IdentityService.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
@Aspect
public class UserActivityLogger {
    private static final Logger logger = LoggerFactory.getLogger(UserActivityLogger.class);

    //named pointcut
//    @Around(value = "execution(*com.example.IdentityService.controllers.AuthController.*(..))")
    public Object logUserActivity(ProceedingJoinPoint joinPoint) throws Throwable {
        String method = joinPoint.getSignature().getName();
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String ipAddress = requestAttributes.getRequest().getRemoteAddr();
        logger.info("User activity started: " + method + ",IP address: " + ipAddress);
        Object result=joinPoint.proceed();
        logger.info("User activity finished: " + method);
        return result;
    }
}
