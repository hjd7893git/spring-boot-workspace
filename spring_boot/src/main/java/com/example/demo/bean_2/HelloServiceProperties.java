package com.example.demo.bean_2;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(value = "hello")
public class HelloServiceProperties {
    private static final String MSG = "world";
    private String msg = MSG;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}