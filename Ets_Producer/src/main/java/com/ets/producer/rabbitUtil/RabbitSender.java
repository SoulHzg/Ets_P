package com.ets.producer.rabbitUtil;

import com.ets.common.bean.UserBean;
import com.ets.producer.config.RabbitMQConfig;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitSender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sendDirectQueue(){
        UserBean userBean = new UserBean();
        userBean.setId("15672");
        userBean.setName("RabbitMQ");
        userBean.setAge("1");
        System.out.println("rabbitMQ发送消息内容："+userBean);
        amqpTemplate.convertAndSend(RabbitMQConfig.queueName,userBean);
    }
}
