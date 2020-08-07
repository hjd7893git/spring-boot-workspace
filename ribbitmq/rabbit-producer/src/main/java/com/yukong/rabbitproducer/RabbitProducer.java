package com.yukong.rabbitproducer;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.ws.Action;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author yukong
 * @date 2018/8/22
 * @description rabbit消息生产者
 */
@Component
public class RabbitProducer {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void stringSend() {
        Date date = new Date();
        String dateString = new SimpleDateFormat("YYYY-mm-DD hh:MM:ss").format(date);
        System.out.println("[string] send msg:" + dateString);
        this.rabbitTemplate.convertAndSend("string", dateString);
    }

    public void directSend(){
        Date date = new Date();
        String dateString = new SimpleDateFormat("YYYY-mm-DD hh:MM:ss").format(date);
        System.out.println("[direct] send msg:" + dateString);
        //注意 第一个参数是我么难度交换机的名称，第二个参数是routrtKey key值是一对一的，要完全匹配
        //其实该模式和普通模式一样，如果绑定了交换器和key，就需要一一进行模式匹配，否则可以按照普通模式来，进行通道绑定即可
        this.rabbitTemplate.convertAndSend("directExchange","hjd", dateString);
    }

    public void fanoutSend() {
        Date date = new Date();
        String dateString = new SimpleDateFormat("YYYY-mm-DD hh:MM:ss").format(date);
        System.out.println("[fanout] send msg:" + dateString);
        // 注意 第一个参数是我们交换机的名称 ，第二个参数是routerKey 我们不用管空着就可以，第三个是你要发送的消息
        this.rabbitTemplate.convertAndSend("fanoutExchange", "", dateString);
    }

    public void topicTopic1Send() {
        Date date = new Date();
        String dateString = new SimpleDateFormat("YYYY-mm-DD hh:MM:ss").format(date);
        dateString = "[topic.msg] send msg:" + dateString;
        System.out.println(dateString);
        // 注意 第一个参数是我们交换机的名称 ，第二个参数是routerKey topic.msg，第三个是你要发送的消息
        // 这条信息将会被 topic.a  topic.b接收
        this.rabbitTemplate.convertAndSend("topicExchange", "topic.msg", dateString);
    }

    public void topicTopic2Send() {
        Date date = new Date();
        String dateString = new SimpleDateFormat("YYYY-mm-DD hh:MM:ss").format(date);
        dateString = "[topic.good.msg] send msg:" + dateString;
        System.out.println(dateString);
        // 注意 第一个参数是我们交换机的名称 ，第二个参数是routerKey ，第三个是你要发送的消息
        // 这条信息将会被topic.b接收
        this.rabbitTemplate.convertAndSend("topicExchange", "topic.good.msg", dateString);
    }

    public void topicTopic3Send() {
        Date date = new Date();
        String dateString = new SimpleDateFormat("YYYY-mm-DD hh:MM:ss").format(date);
        dateString = "[topic.m.z] send msg:" + dateString;
        System.out.println(dateString);
        // 注意 第一个参数是我们交换机的名称 ，第二个参数是routerKey ，第三个是你要发送的消息
        // 这条信息将会被topic.b、topic.c接收
        this.rabbitTemplate.convertAndSend("topicExchange", "topic.m.z", dateString);
    }

}
