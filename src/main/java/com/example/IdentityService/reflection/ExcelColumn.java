package com.example.IdentityService.reflection;

import java.lang.annotation.*;

@Target({ ElementType.TYPE, ElementType.METHOD, ElementType.FIELD,
        ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelColumn {
    int index() default 0;
    String name() default "";
}
