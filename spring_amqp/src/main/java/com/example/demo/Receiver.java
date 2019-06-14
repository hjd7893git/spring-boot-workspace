package com.example.demo;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {
    //使用@RabbitListener来监听RabbitMQ的目的地发送的消息，通过queues属性指定要监听的目的地
    @RabbitListener(queues = "my-queue")
    public void receiveMessage(String message) {
        System.out.println("接受到: <" + message + ">");
    }

    @RabbitListener(queues = "my-hjd")
    public void receiveMessage_2(String message) {
        System.out.println("hjd接受到:<" + message + ">");
    }
}