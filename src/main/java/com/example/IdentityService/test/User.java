package com.example.IdentityService.test;

import com.example.IdentityService.reflection.ExcelColumn;
import lombok.Data;

@Data
public class User {
    @ExcelColumn
    private String id;
    @ExcelColumn(index = 1)
    private String name;
    @ExcelColumn(index = 2)
    private String age;
    @ExcelColumn(index = 3)
    public String address;

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
