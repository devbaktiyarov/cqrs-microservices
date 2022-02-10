package com.arsenbaktiyarov.core.command;

import lombok.Data;


@Data
public class PaymentProcessedEvent {
    private String paymentId;
    private String orderId;
}
