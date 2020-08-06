package com.example.demo.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2019/5/24.
 */

/**
 * 将配置文件中配置的每一个属性的值，映射到这个组件中
 *  * @ConfigurationProperties：告诉SpringBoot将本类中的所有属性和配置文件中相关的配置进行绑定；
 *  *      prefix = "person"：配置文件中哪个下面的所有属性进行一一映射
 */
@Component    //注入组件
@ConfigurationProperties(prefix = "user")   //bena与properties相关联【全局，在application中配置的属性，非此文件中的属性就都取不出来】
@PropertySource({"classpath:user.properties"}) //：加载指定的配置文件；需要和@ConfigurationProperties注解配合使用【可加载其它的文件】
public class User {
    private String names;
    private int age;

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
