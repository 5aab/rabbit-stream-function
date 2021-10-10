package com.sample.functional;

import com.sample.functional.service.domain.FoodOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Consumer;
import java.util.function.Function;

@Slf4j
@SpringBootApplication
//https://spring.io/blog/2019/10/31/spring-cloud-stream-event-routing
public class RabbitSpringCloudStreamApp {

    public static void main(String[] args) {
        SpringApplication.run(RabbitSpringCloudStreamApp.class, args);
    }

    @Bean
    public Function<FoodOrder, String> high() {
        return foodOrder -> {
            log.info("Received Spicy high: {}", foodOrder);
            return "Received Spicy high: {}";
        };
    }

    @Bean
    public Consumer<FoodOrder> low() {
        return foodOrder -> log.info("Received Spicy low: {}", foodOrder);
    }

}
