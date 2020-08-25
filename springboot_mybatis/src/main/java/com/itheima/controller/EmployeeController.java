package com.itheima.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.itheima.service.EmloyeeService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Date;

@RestController
@RequestMapping("/emp")
@EnableSwagger2
@Api(tags = "EmployeeController", description = "用例测试") //可选
public class EmployeeController {
    @Autowired
    EmloyeeService emloyeeService;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @GetMapping(value = "/get")
    public Date testdata() {
        return emloyeeService.getById(26).getDate();
    }

}
