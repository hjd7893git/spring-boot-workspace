package com.example.demo.dao;

import javax.annotation.Resource;

import com.example.demo.daomain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;


@Repository
public class PersonDao {
    @Autowired
    StringRedisTemplate stringRedisTemplate; //Spring Boot已为我们配置StringRedisTemplate，在此处可以直接注入。

    @Resource(name = "stringRedisTemplate")   //名称不可更改
    ValueOperations<String, String> valOpsStr; //可以使用@Resource注解指定stringRedisTemplate，可注入基于字符串的简单属性操作方法。

    @Autowired
    RedisTemplate<Object, Object> redisTemplate; //Spring Boot已为我们配置RedisTemplate，在此处可以直接注入。

    @Resource(name = "redisTemplate")
    ValueOperations<Object, Object> valOps; //可以使用@Resource注解指定redisTemplate，可注入基于对象的简单属性操作方法；

    public void stringRedisTemplateDemo() { //通过set方法，存储字符串类型。
        valOpsStr.set("xx", "yy");
        redisTemplate.opsForValue().set("hjd","黄俊东");
    }

    public void save(Person person) {
        valOps.set(person.getId(), person); //通过set方法，存储对象类型。
    }

    public String getString() {
        System.out.println(redisTemplate.opsForValue().get("hjd"));

//        System.out.println(redisTemplate.opsForValue().get("xx")); //不可取出

        System.out.println(valOps.get("hjd"));

        return valOpsStr.get("xx");//通过get方法，获得字符串。
    }

    public Person getPerson() {
        return (Person) valOps.get("1");//通过get方法，获得对象
    }
}