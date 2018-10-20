package com.punjab.de.janwar.service;

import com.punjab.de.janwar.domain.NotificationMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class RabbitServiceImpl implements RabbitService{

    private ThreadPoolTaskExecutor concurrentTaskExecutor;

    @Override
    public void printMessage(NotificationMessage message){
        concurrentTaskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                log.info("Flow Reachable : {}",message);
            }
        });

    }
}
