package com.arsenbaktiyarov.orderservice.event;

import com.arsenbaktiyarov.orderservice.model.OrderStatus;
import lombok.Value;

@Value
public class OrderApprovedEvent {
    private String orderId;
    private OrderStatus orderStatus = OrderStatus.APPROVED;
}
