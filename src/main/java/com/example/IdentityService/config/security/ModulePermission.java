package com.example.IdentityService.config.security;

import com.example.IdentityService.utils.MODULE;
import com.example.IdentityService.utils.PERMISSION;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ModulePermission {

    MODULE module();

    PERMISSION permission() default PERMISSION.READ;
}
