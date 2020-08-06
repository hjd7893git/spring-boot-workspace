package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: spring-boot-workspace
 * @description: Configuration测试说明
 * @author: hjd
 * @create: 2020-05-15 15:59
 **/
@RestController
public class DemoApplicationForMvc2 {
    @RequestMapping("/d2")
    public String ret(){
        return "Configuration测试说明";
    }
}
