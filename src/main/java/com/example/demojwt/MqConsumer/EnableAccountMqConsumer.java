package com.example.demojwt.MqConsumer;

import com.example.demojwt.models.UserMongoDB;
import com.example.demojwt.repository.UserRepository;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RabbitListener(queues = "enable_account")
@Component
public class EnableAccountMqConsumer {

    @Autowired
    UserRepository userRepository;

    @RabbitHandler
    public void consume(String id){
        System.out.println(">>>> received= " + id);
        Optional<UserMongoDB> userMongoDBOptional = userRepository.findById(id);

        if (userMongoDBOptional.isPresent()){
            UserMongoDB user = userMongoDBOptional.get();
            user.setEnabled(true);
            userRepository.save(user);
        }
    }
}
