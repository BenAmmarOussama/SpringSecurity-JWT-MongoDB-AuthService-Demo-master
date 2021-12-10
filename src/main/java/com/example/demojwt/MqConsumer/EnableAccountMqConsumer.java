package com.example.demojwt.MqConsumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@RabbitListener(queues = "enable_account")
@Component
public class EnableAccountMqConsumer {

    @RabbitHandler
    public void consume(String id){
        System.out.println(">>>> received= " + id);
    }
}
