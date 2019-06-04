package com.example.demo.domain;

/**
 * Created by Administrator on 2019/6/3.
 */

import org.hibernate.annotations.NamedQuery;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity //1注解指明这是一个和数据库表映射的实体类
@NamedQuery(name = "Person.withNameAndAddressNamedQuery", query = "select p from Person p where p.name=?1 and address=?2")
public class Person {
    @Id //2注解指明这个属性映射为数据库的主键。
    @GeneratedValue(strategy = GenerationType.IDENTITY) //3注解默认使用主键生成方式为自增,
    private Long id;
    private String name;
    private Integer age;
    private String address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Person() {

        super();
    }
    public Person(String name, Integer age, String address) {
        super();
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public Person(Long id, String name, Integer age, String address) {
        super();
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
    }
// 省略setter、getter
    /**
     * 在这里你可能注意到，我们没有通过@Column注解来注解
     普通属性，@Column是用来映射属性名和字段名，不注解的
     时候hibernate会自动根据属性名生成数据表的字段名。如属性
     名name映射成字段NAME；多字母属性如testName会自动映射
     为TEST_NAME。表名的映射规则也如此。
     */
}

