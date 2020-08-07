package com.itheima.mapper;

import com.itheima.domain.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Mapper 是 Mybatis 的注解，和 Spring 没有关系，@Repository 是 Spring 的注解，用于声明一个 Bean。（重要）
@Repository
@Mapper//表明当前接口是一个Mapper，被Mybatis框架扫描
public interface UserMapper {

    List<User> findAll();

    User findById(Integer id);

    void save(User user);

    void update(User user);

    void delete(Integer id);

}