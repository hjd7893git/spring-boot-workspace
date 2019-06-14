package com.example.demo;

import com.example.demo.jms.Msg;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Controller;

/**
 * Created by Administrator on 2019/6/14.
 */
@Controller
public class T2 {
    @Autowired
    JmsTemplate jmsTemplate;
    @Test
    public void t1(){
        jmsTemplate.send("my-hjd",new Msg());
    }
}
