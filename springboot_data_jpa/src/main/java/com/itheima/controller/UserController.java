package com.itheima.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itheima.domain.User;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    RedisTemplate redisTemplate;

    //查询所有
    @RequestMapping("/findAll")
    public String findAll() throws JsonProcessingException {
        List<User> users = userService.findAll();

        String allusers = (String) redisTemplate.boundValueOps("user.findAll").get();
        if(StringUtils.isEmpty(allusers)){
            //从数据库中获取
            ObjectMapper objectMapper = new ObjectMapper();
            allusers = objectMapper.writeValueAsString(users);
            System.out.println("-------------------从数据库查询数据----------------------");
            redisTemplate.boundValueOps("user.findAll").set(allusers);
            return allusers+"从数据库查询数据";

        }//从缓存中获取
        System.out.println("-------------------从缓存中查询数据----------------------");
        return allusers+"从缓存中查询数据";
    }

    //根据id查询
    @RequestMapping("/findById/{Id}")
    public User findById(@PathVariable("Id") Integer id) {
        return userService.findById(id);
    }

    //新增
    @RequestMapping("/save")
    public void save(@RequestBody User user) {
        userService.save(user);
    }

    //修改
    @RequestMapping("/update")
    public void update(User user) {
        userService.update(user);
    }

    //删除
    @RequestMapping("/delete")
    public void delete(Integer id) {
        userService.delete(id);
    }
}
