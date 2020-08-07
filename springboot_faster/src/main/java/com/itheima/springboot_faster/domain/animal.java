package com.itheima.springboot_faster.domain;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class animal{
    private String name;
    private Integer age;

    //有参构造函数，前提必须有无参构造函数，不然自动配置的属性导入失败
//    public animal(String name, int age) {
//        this.name = name;
//        this.age = age;
//    }

    @Override
    public String toString() {
        return "animal{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
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
