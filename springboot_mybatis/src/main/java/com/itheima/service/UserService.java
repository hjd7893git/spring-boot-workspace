package com.itheima.service;

import com.itheima.domain.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findById(Integer id);

    void save(User user);

    void update(User user);

    void delete(Integer id);
}
