package com.example.IdentityService.test;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TestAspect {
    @Before(value = "@annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void myMethod() {
        System.out.println("Annotation getMapping is invoke !");
    }
}
