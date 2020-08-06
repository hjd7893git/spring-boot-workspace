package com.example.demo;

import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringAmqpApplication{

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

}
