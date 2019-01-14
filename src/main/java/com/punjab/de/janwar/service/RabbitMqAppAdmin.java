package com.punjab.de.janwar.service;

import lombok.AllArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RabbitMqAppAdmin {

    public RabbitAdmin rabbitAdmin;

    public String queue(String queueName){
        return rabbitAdmin.declareQueue(new Queue(queueName));
    }


}
