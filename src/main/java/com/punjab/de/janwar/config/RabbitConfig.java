package com.punjab.de.janwar.config;

import lombok.AllArgsConstructor;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@AllArgsConstructor
public class RabbitConfig {

     private ConnectionFactory connectionFactory;

    @Bean
    TopicExchange worksExchange() {
        return new TopicExchange("work.exchange", true, false);
    }

    @Bean
    public ThreadPoolTaskExecutor concurrentTaskExecutor(){
        ThreadPoolTaskExecutor threadPoolTaskExecutor= new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(12);
        return threadPoolTaskExecutor;
    }

    @Bean
    public RabbitAdmin rabbitAdmin(){
        return new RabbitAdmin(connectionFactory);
    }
}

