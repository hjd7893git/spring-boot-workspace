package com.itheima.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.domain.User;
import com.itheima.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@RestController
@RequestMapping("/user")
@EnableSwagger2
@Api(tags = "UserController", description = "用例测试") //可选
public class UserController {

    @Autowired
    UserService userService;
    //查询所有
    @ApiOperation(value = "查询所有") //可选
    @GetMapping("/findAll")
    public List<User> findAll() throws JsonProcessingException {
         return userService.findAll();
    }

    //根据id查询
    @PostMapping("/findById")
    public User findById(Integer id) {
        return userService.findById(id);
    }

    //新增
    @PutMapping("/save")
    //User user 时，发送的是url 拼接
    // @RequestBody时发送的时post请求的封装的数据
    public void save( @ApiParam("新增的记录")  @RequestBody User user) {
        userService.save(user);
    }

    //修改
    @PostMapping("/update")
    public void update(@RequestBody User user) {
        userService.update(user);
    }

    //删除
    @DeleteMapping("/delete")
    public void delete(Integer id) {
        userService.delete(id);
    }

    @GetMapping("findByNS")
    public List<User> getUserList(Integer pageNum, Integer pageSize) {
        Page page2 = PageHelper.startPage(pageNum, pageSize);   //1 第一步
        List<User> userList = userService.findAll();            //2 第二步

        PageInfo<User> page = new PageInfo<User>(userList);
        System.out.println("总数量：" + page.getTotal());
        System.out.println("当前页查询记录：" + page.getList().size());
        System.out.println("当前页码：" + page.getPageNum());
        System.out.println("每页显示数量：" + page.getPageSize());
        System.out.println("总页：" + page.getPages());
        return userList;
    }
}
