package com.punjab.de.janwar.service;

import com.punjab.de.janwar.domain.NotificationMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RabbitServiceImpl implements RabbitService{

    @Override
    public void printMessage(NotificationMessage message){
        log.info("Flow Reachable : {}",message);
    }
}
