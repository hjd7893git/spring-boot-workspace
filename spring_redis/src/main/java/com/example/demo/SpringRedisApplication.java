package com.example.demo;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.net.UnknownHostException;

@SpringBootApplication
public class SpringRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringRedisApplication.class, args);
    }

    /**
     * 自定义序列模板
     *
     * SpringBoot为我们自动配置了RedisTemplate，而
     RedisTemplate使用的是JdkSerializationRedisSerializer，这个对我们演示RedisClient很不直观，因为
     JdkSerializationRedisSerializer使用二级制形式存储数据，在此我们将自己配置RedisTemplate并定义Serializer。
     * @param redisConnectionFactory
     * @return
     * @throws UnknownHostException
     */
    @Bean
    @SuppressWarnings({"rawtypes", "unchecked"})
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
        RedisTemplate<Object, Object> template = new RedisTemplate<Object, Object>();
        template.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        template.setValueSerializer(jackson2JsonRedisSerializer); //1
        template.setKeySerializer(new StringRedisSerializer()); //2
        template.afterPropertiesSet();
        return template;
    }
}
