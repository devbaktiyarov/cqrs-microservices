package com.arsenbaktiyarov.orderservice.controller;

import com.arsenbaktiyarov.orderservice.command.CreateOrderCommand;
import com.arsenbaktiyarov.orderservice.model.Order;
import com.arsenbaktiyarov.orderservice.model.OrderStatus;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrdersCommandController {

    private final CommandGateway commandGateway;

    public OrdersCommandController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public String createOrder(@RequestBody Order order) {
        String userId = "27b95829-4f3f-4ddf-8983-151ba010e35b";

        CreateOrderCommand createOrderCommand = CreateOrderCommand.builder()
                .productId(order.getProductId())
                .userId(userId)
                .orderId(UUID.randomUUID().toString())
                .addressId(order.getAddressId())
                .quantity(order.getQuantity())
                .orderStatus(OrderStatus.CREATED).build();

        String returnedValue;

        try {
            returnedValue = commandGateway.sendAndWait(createOrderCommand);
        } catch (Exception e) {
            returnedValue = e.getLocalizedMessage();
        }

        return returnedValue;
    }
}
