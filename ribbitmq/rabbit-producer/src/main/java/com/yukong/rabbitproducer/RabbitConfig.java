package com.yukong.rabbitproducer;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.management.Query;

/**
 * @author yukong
 * @date 2018/8/22
 * @description rabbitmq配置类
 */
@Configuration
public class RabbitConfig {

    /**
     * 定义队列名
     */
    private final static String STRING = "string";


    /**
     * 定义string队列
     *
     * @return
     */
    //====================普通 模式=====================
    @Bean
    public Queue string() {
        return new Queue(STRING);
    }

    //====================Direct 模式=====================
    //其实该模式和普通模式一样，如果绑定了交换器和key，就需要一一进行模式匹配，否则可以按照普通模式来，进行通道绑定即可
    //对DirectExchange进行改造
    //binding key一致
    @Bean
    public Queue directq() {return new Queue("direct.queue");}

    @Bean
    DirectExchange directExchange(){
        return new DirectExchange("directExchange");
    }
    @Bean
    Binding bindingExchangeWithDirect(){

        return BindingBuilder.bind(directq()).to(directExchange()).with("hjd");
    }
//    }

    //=================== fanout 模式  ====================

    @Bean()
    public Queue fanoutA() {
        return new Queue("fanout.a");
    }

    @Bean
    public Queue fanoutB() {
        return new Queue("fanout.b");
    }

    @Bean
    public Queue fanoutC() {
        return new Queue("fanout.c");
    }

    /**
     * 定义个fanout交换器
     *
     * @return
     */
    @Bean
    FanoutExchange fanoutExchange() {
        // 定义一个名为fanoutExchange的fanout交换器
        return new FanoutExchange("fanoutExchange");
    }

    /**
     * 将定义的fanoutA队列与fanoutExchange交换机绑定
     *    方法参数默认注入方式为Autowired：
     *    1：复杂类型可以通过@Qualifier(value="dataSource")限定;
     *    2：对于普通类型使用@Value指定;
     *
     * @return
     */
    @Bean
    public Binding bindingExchangeWithA(Queue fanoutA, FanoutExchange fanoutExchange) {
        return BindingBuilder
                .bind(fanoutA)
                .to(fanoutExchange);
    }

    /**
     * 将定义的fanoutB队列与fanoutExchange交换机绑定
     *
     * @return
     */
    @Bean
    public Binding bindingExchangeWithB() {
        return BindingBuilder
                .bind(fanoutB())
                .to(fanoutExchange());
    }

    /**
     * 将定义的fanoutC队列与fanoutExchange交换机绑定
     *
     * @return
     */
    @Bean
    public Binding bindingExchangeWithC() {
        return BindingBuilder
                .bind(fanoutC())
                .to(fanoutExchange());
    }


    //#################topic模式########################

    @Bean
    public Queue topiocA() {
        return new Queue("topic.a");
    }

    @Bean
    public Queue topicB() {
        return new Queue("topic.b");
    }

    @Bean
    public Queue topicC() {
        return new Queue("topic.c");
    }

    /**
     * 定义个topic交换器
     *
     * @return
     */
    @Bean
    TopicExchange topicExchange() {
        // 定义一个名为fanoutExchange的fanout交换器
        return (TopicExchange) ExchangeBuilder.topicExchange("topicExchange").durable(true).build(); //另一种定义的方法

//        return new TopicExchange("topicExchange");
    }

    /**
     * 将定义的topicA队列与topicExchange交换机绑定
     *
     * @return
     */
    @Bean
    public Binding bindingTopicExchangeWithA() {
        return BindingBuilder
                .bind(topiocA())
                .to(topicExchange())
                .with("topic.msg");
    }

    /**
     * 将定义的topicB队列与topicExchange交换机绑定
     *
     * @return
     */
    @Bean
    public Binding bindingTopicExchangeWithB() {
        return BindingBuilder
                .bind(topicB())
                .to(topicExchange())
                .with("topic.#");
    }

    /**
     * 将定义的topicC队列与topicExchange交换机绑定
     *
     * @return
     */
    @Bean
    public Binding bindingTopicExchangeWithC() {
        return BindingBuilder
                .bind(topicC())
                .to(topicExchange())
                .with("topic.*.z");
    }
}
