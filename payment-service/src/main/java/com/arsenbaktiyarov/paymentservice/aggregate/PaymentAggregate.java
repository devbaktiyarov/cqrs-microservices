package com.arsenbaktiyarov.paymentservice.aggregate;

import com.arsenbaktiyarov.core.command.ProcessPaymentCommand;
import com.arsenbaktiyarov.core.command.PaymentProcessedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
public class PaymentAggregate {
    @AggregateIdentifier
    private String paymentId;
    private String orderId;

    public PaymentAggregate() {
    }

    @CommandHandler
    public PaymentAggregate(ProcessPaymentCommand processPaymentCommand) {
        if (processPaymentCommand.getOrderId() == null) {
            throw new IllegalArgumentException("In ProcessPaymentCommand orderId is null");
        }
        PaymentProcessedEvent paymentProcessedEvent = new PaymentProcessedEvent();
        BeanUtils.copyProperties(processPaymentCommand, paymentProcessedEvent);
        AggregateLifecycle.apply(paymentProcessedEvent);
    }

    @EventSourcingHandler
    public void on(PaymentProcessedEvent paymentProcessedEvent) {
        this.paymentId = paymentProcessedEvent.getPaymentId();
        this.orderId = paymentProcessedEvent.getOrderId();
    }
}
