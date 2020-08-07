package com.itheima.domain;

import javax.persistence.*;

@Entity   //必须 @Entity 说明这个 class 是实体类，并且使用默认的 orm 规则，即 class 名即数据库表中表名，class 字段名即表中的字段名。
@Table(name = "user_tbl") //非必需
//如果同时使用了 @Entity(name="user") 和 @Table(name="users")，最终的对应的表名必须是哪个？答案是 user，这说明优先级：@Table > @Entity。
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String password;
    private String name;


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
