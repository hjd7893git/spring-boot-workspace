package com.example.demo;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by Administrator on 2019/11/18.
 */
@SpringBootApplication
public class SendMain implements CommandLineRunner {
    @Autowired
    RabbitTemplate rabbitTemplate; //1 可注入Spring Boot为我们自动配置好的RabbitTemplate。

    public static void main(String[] args) {
        SpringApplication.run(SendMain.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        rabbitTemplate.convertAndSend("my-queue", "来自 RabbitMQ的问候"); //3 通过RabbitTemplate的convertAndSend方法向队列my-queue发送消息
        rabbitTemplate.convertAndSend("my-queue", "来自 RabbitMQ的问候2"); //3 通过RabbitTemplate的convertAndSend方法向队列my-queue发送消息
        rabbitTemplate.convertAndSend("my-queue", "来自 RabbitMQ的问候3"); //3 通过RabbitTemplate的convertAndSend方法向队列my-queue发送消息
        rabbitTemplate.convertAndSend("my-hjd", "来自 RabbitMQ的问候11111"); //3 通过RabbitTemplate的convertAndSend方法向队列my-queue发送消息
        rabbitTemplate.convertAndSend("my-hjd", "来自 RabbitMQ的问候333331"); //3 通过RabbitTemplate的convertAndSend方法向队列my-queue发送消息
        rabbitTemplate.convertAndSend("my-hjd", "来自 RabbitMQ的问候44444444"); //3 通过RabbitTemplate的convertAndSend方法向队列my-queue发送消息
        rabbitTemplate.convertAndSend("my-hjd", "来自 RabbitMQ的问候555555"); //3 通过RabbitTemplate的convertAndSend方法向队列my-queue发送消息
    }
}
