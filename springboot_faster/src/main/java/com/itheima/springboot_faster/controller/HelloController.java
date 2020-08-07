package com.itheima.springboot_faster.controller;

import com.itheima.springboot_faster.domain.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@Slf4j
public class HelloController {

    @Autowired
    Person person;

    @RequestMapping("/hello")
    public String hello(){
        return "hello world!!!! ====> "+person;
    }
}
