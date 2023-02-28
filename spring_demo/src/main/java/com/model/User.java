package com.model;

import lombok.Data;

@Data
public class User {
    private Long id;
    private String userName;
    private String note;
    private String name;
    private String sex;
    private Integer age;

    public User(){}
    public User (int age) {
        this.age = age;
    }
}