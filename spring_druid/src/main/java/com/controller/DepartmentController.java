package com.controller;

import com.dao.DepartmentDruid;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.*;
import com.service.DepartmentService;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by hjd on 2020-07-15 17:51
 * -->
 **/
@EnableSwagger2
@Api(description ="接口测试")
@Controller
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    @ResponseBody
    @PostMapping("/add")
    public void add(@RequestBody DepartmentDruid department){
        departmentService.add(department);
    }

    @ResponseBody
    @GetMapping("findbyid/{id}")
    public DepartmentDruid findById(@PathVariable("id") int id){
        DepartmentDruid department = departmentService.findById(id);
        return department;
    }
}
