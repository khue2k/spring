package com.example.springsecurity.test;

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

class Test {
    private static boolean isGetter(Method method) {
        if (!method.getName().startsWith("get")) {
            return false;
        } else if (method.getParameters().length != 0) {
            return false;
        } else if (void.class.equals(method.getReturnType())) {
            return false;
        }
        return true;
    }

    private static boolean isSetter(Method method) {
        if (!method.getName().startsWith("set")) {
            return false;
        } else if (method.getParameters().length != 1) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        Class<Cat> aClass = Cat.class;
        for (Method declaredMethod : aClass.getDeclaredMethods()) {
            System.out.println("Method name : " + declaredMethod.getName());
            System.out.println("Is getter : " + Test.isGetter(declaredMethod));
            System.out.println("Is setter : " + Test.isSetter(declaredMethod));
        }
    }
}