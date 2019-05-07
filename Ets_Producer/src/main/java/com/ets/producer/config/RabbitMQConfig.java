package com.ets.producer.config;

import com.rabbitmq.client.AMQP;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String queueName = "firstQueue";

    @Bean
    public Queue directQueue(){
        //第一个参数：队列名称  第二个参数：队列是否持久化
        return new Queue(queueName,true);
    }
}
