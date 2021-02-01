package com.punjab.de.janwar.stream.service;

import com.punjab.de.janwar.stream.service.domain.FoodOrder;
import lombok.AllArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class FoodOrderController {

    private StreamBridge streamBridge;
    private static final String SUPPLIER_BINDING_NAME = "foodordersupplier-out-0";

    @PostMapping("order")
    public String orderFood(){
        FoodOrder foodOrder =new FoodOrder("HK","HK","HK");
        Message<FoodOrder> foodOrderMessage = MessageBuilder.withPayload(foodOrder).setHeader("restaurant", "HK").setHeader("spicy", "true").build();
        streamBridge.send(SUPPLIER_BINDING_NAME,foodOrderMessage);
        System.out.println(foodOrder.toString());
        return "food ordered!";
    }
}
