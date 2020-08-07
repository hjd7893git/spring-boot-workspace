package com.itheima.springboot_faster.domain;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@ConfigurationProperties(prefix = "person")
@Component
public class Person {
    /**
     * 如果说，我们只是在某个业务逻辑中需要获取一下配置文件中的某项值，使用@Value；
     * 如果说，我们专门编写了一个javaBean来和配置文件进行映射，我们就直接使用@ConfigurationProperties；
     */
//    @Value("${person.username}")
    private String username;
    private Integer age;
    private Date birthday;
//    @Value("${person.citys}") //todo:会出错。因为value不能解析复杂的数据
    private String[] citys;
//    @Value("${person.animals}")
    private List<animal> animals;

    @Override
    public String toString() {
        return "Person{" +
                "username='" + username + '\'' +
                ", age=" + age +
                ", birthday=" + birthday +
                ", citys=" + Arrays.toString(citys) +
                ", animals=" + animals +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String[] getCitys() {
        return citys;
    }

    public void setCitys(String[] citys) {
        this.citys = citys;
    }

    public List<animal> getAnimals() {
        return animals;
    }

    public void setAnimals(List<animal> animals) {
        this.animals = animals;
    }


}
