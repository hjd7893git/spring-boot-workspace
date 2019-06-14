package com.example.demo;

import com.example.demo.jms.Msg;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringJmsApplicationTests {
	@Autowired
	JmsTemplate jmsTemplate;

	@Test
	public void contextLoads() {
		jmsTemplate.send("my-hjd", new Msg()); //3
	}

}
