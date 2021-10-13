package com.sample.functional.service.rest;

import com.sample.functional.service.domain.FoodOrder;
import lombok.AllArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@AllArgsConstructor
public class FoodOrderController {

    private StreamBridge streamBridge;
    private static final String SUPPLIER_BINDING_NAME = "foodordersupplier-out-0";

    @PostMapping("order")
    public String orderFood() {
        FoodOrder foodOrder = new FoodOrder("HK", "HK", "HK");
        Map<String, Object> headers = new HashMap<>();
        headers.put("relay-destination", SUPPLIER_BINDING_NAME);
        headers.put("spicy", "high");
        Message<FoodOrder> finalMessage = MessageBuilder.createMessage(foodOrder, new MessageHeaders(headers));
        streamBridge.send(SUPPLIER_BINDING_NAME, finalMessage);
        System.out.println("Sending request for " + foodOrder);
        return "food ordered!";
    }
}
