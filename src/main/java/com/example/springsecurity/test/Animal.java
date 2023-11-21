package com.example.springsecurity.test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.*;

public abstract class Animal {


    public String getLocation() {
        return "Earth";
    }

    public abstract int getNumberOfLegs();

}

interface Say {

    public String say();
}

class Cat extends Animal implements Say {

    public static final String SAY = "Meo meo";
    public static final int NUMBER_OF_LEGS = 4;

    // Private field.
    private String name;

    // Private field
    public int age;

    private boolean check;

    public Cat() {

    }

    public Cat(String name) {
        this.name = name;
        this.age = 1;
    }

    public Cat(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return this.name;
    }

    // Private Method.
    private void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    @Override
    public String say() {
        return SAY;
    }

    @Override
    public int getNumberOfLegs() {
        return NUMBER_OF_LEGS;
    }

}

// Annotation này có thể sử dụng tại thời điểm chạy (Runtime) của chương trình.
@Retention(RetentionPolicy.RUNTIME)
// Có thể dùng cho class,interface, method, field, parameter.
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD,
        ElementType.PARAMETER})
@interface MyAnnotation {

    String name();

    String value() default "";
}
@MyAnnotation(name = "khue",value = "200")
class Test {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        Class<Test> aClass= Test.class;
    }
}