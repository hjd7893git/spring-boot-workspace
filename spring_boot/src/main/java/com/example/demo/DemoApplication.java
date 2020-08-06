package com.example.demo;

import com.example.demo.bean.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.nio.charset.Charset;


@RestController        //springMVC 控制注解
@SpringBootApplication  //spring boot 核心
//@ImportResource({"classpath:some-context.xml","",""})	//导入Spring的配置文件，让配置文件里面的内容生效；
public class DemoApplication {
    @Value("${book.name}")  //注入application.properties配置文件中的属性
    private String bookName;

    @Resource // @Autowired //注入bean中的@ConfigurationProperties(prefix = "user") 组件配置
    private User user;

    @RequestMapping("/")
    public String index() {
        return "hello! Spring Boot!" + bookName +user.getNames() + user.getAge();
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    //消息转化器
    @Bean
    public StringHttpMessageConverter stringHttpMessageConverter(){
        System.out.println("消息转化器");
        StringHttpMessageConverter converter  = new StringHttpMessageConverter(Charset.forName("UTF-8"));
        return converter;
    }


}
