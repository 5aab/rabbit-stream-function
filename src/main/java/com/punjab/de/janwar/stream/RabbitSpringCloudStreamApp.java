package com.punjab.de.janwar.stream;

import com.punjab.de.janwar.stream.service.domain.FoodOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.function.compiler.FunctionCompiler;
import org.springframework.context.annotation.Bean;

import java.util.function.Consumer;
import java.util.function.Function;

@Slf4j
@SpringBootApplication
public class RabbitSpringCloudStreamApp  {

    public static void main(String[] args){
        SpringApplication.run(RabbitSpringCloudStreamApp.class, args);
    }

    @Bean
    public <T, R> FunctionCompiler<T, R> compiler() {
        return new FunctionCompiler<>();
    }

    @Bean
    public Function<String, String> uppercase() {
        return value -> value.toUpperCase();
    }


    @Bean
    public Consumer<FoodOrder> foodorder() {
        return foodOrder -> {
            log.info("Received Functionally: {}", foodOrder.toString());
        };
    }
}
