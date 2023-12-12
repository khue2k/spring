package com.example.springsecurity.config.security;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class PermissionAspect {
    @Before(value = "@annotation(modulePermission)")
    public void before(ModulePermission modulePermission) {
    }
}
