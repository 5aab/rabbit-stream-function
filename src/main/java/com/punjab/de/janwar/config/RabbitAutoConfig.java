package com.punjab.de.janwar.config;

import com.punjab.de.janwar.domain.NotificationMessage;
import com.punjab.de.janwar.service.RabbitServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.amqp.dsl.Amqp;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Transformers;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;


@Configuration
@AllArgsConstructor
@EnableAsync
@EnableRetry
@EnableCaching
public class RabbitAutoConfig {

    //@Qualifier("producerTemplate")
    private RabbitTemplate rabbitTemplate;
    private ConnectionFactory connectionFactory;
    private RabbitServiceImpl rabbitService;



    @Bean
    Queue integrationQueue() {
        return new Queue("integrationQueue", true);
    }

    @Bean
    public IntegrationFlow inboundFlow() {
        return IntegrationFlows.from(
                Amqp.inboundAdapter(connectionFactory, "integrationQueue")
                        .configureContainer(c->c.concurrentConsumers(12)))
                .transform(Transformers.fromJson(NotificationMessage.class))
                .handle(rabbitService, "printMessage")
                .get();
    }

    @Bean
    public IntegrationFlow toOutboundQueueFlow2() {
        return f -> f.transform(Transformers.toJson()).handle(Amqp.outboundAdapter(rabbitTemplate).routingKey("integrationQueue"));
    }
}
