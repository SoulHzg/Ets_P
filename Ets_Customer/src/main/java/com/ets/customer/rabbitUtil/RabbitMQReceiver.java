package com.ets.customer.rabbitUtil;

import com.ets.common.bean.UserBean;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQReceiver {
    @RabbitListener(queues = "firstQueue")
    public void receiveDirectQueue(UserBean userBean){
        System.out.println("rabbitMQ接收到的消息："+userBean);
    }
}
