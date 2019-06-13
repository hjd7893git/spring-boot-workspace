package com.example.demo;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by Administrator on 2019/6/13.
 */
public class BCryptPasswordEncoderDemo {
    public static void main(String[] args) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        //加密"wyf"
        String encode = bCryptPasswordEncoder.encode("wyf");
        System.out.println(encode);
    }

}
