package com.punjab.de.janwar.config;

import com.punjab.de.janwar.domain.NotificationMessage;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;


@MessagingGateway
public interface RabbitGateway{

    @Gateway(requestChannel = "toOutboundQueueFlow2.input")
    void generate(NotificationMessage notificationMessage);
}