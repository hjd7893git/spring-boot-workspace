package com.itheima.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration//配置类
public class MyConfiguration {

    @Bean//把当前方法返回的对象注入到Spring容器中，<bean id class>
    public RestTemplate restTemplate(){
        return  new RestTemplate();
    }
}

