package com.example.demo;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringAmqpApplication implements CommandLineRunner {

	@Autowired
	RabbitTemplate rabbitTemplate; //1 可注入Spring Boot为我们自动配置好的RabbitTemplate。

	public static void main(String[] args) {
		SpringApplication.run(SpringAmqpApplication.class, args);
	}
	@Bean //2 定义目的地即队列，队列名称为my-queue。
	public Queue wiselyQueue(){
		return new Queue("my-queue");
	}
	@Bean //2 定义目的地即队列，队列名称为my-hjd。
	public Queue wiselyQueue2(){
		return new Queue("my-hjd");
	}
	@Override
	public void run(String... args) throws Exception {
		rabbitTemplate.convertAndSend("my-queue", "来自 RabbitMQ的问候"); //3 通过RabbitTemplate的convertAndSend方法向队列my-queue发送消息
		rabbitTemplate.convertAndSend("my-queue", "来自 RabbitMQ的问候2"); //3 通过RabbitTemplate的convertAndSend方法向队列my-queue发送消息
		rabbitTemplate.convertAndSend("my-queue", "来自 RabbitMQ的问候3"); //3 通过RabbitTemplate的convertAndSend方法向队列my-queue发送消息
		rabbitTemplate.convertAndSend("my-hjd", "来自 RabbitMQ的问候"); //3 通过RabbitTemplate的convertAndSend方法向队列my-queue发送消息
	}

}
