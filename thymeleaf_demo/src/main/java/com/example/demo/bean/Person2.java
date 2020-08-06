package com.example.demo.bean;

public class Person2 {
    private String name;
    private Integer age;

    public Person2() {
        super();
    }

    public Person2(String name, Integer age) {
        super();
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}