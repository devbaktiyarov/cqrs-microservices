package com.arsenbaktiyarov.orderservice.saga;

import com.arsenbaktiyarov.core.command.PaymentProcessedEvent;
import com.arsenbaktiyarov.core.command.ProcessPaymentCommand;
import com.arsenbaktiyarov.core.command.ReserveProductCommand;
import com.arsenbaktiyarov.core.event.ProductReservedEvent;
import com.arsenbaktiyarov.core.model.User;
import com.arsenbaktiyarov.core.query.FetchUserPaymentDetailsQuery;
import com.arsenbaktiyarov.orderservice.command.ApproveOrderCommand;
import com.arsenbaktiyarov.orderservice.event.OrderApprovedEvent;
import com.arsenbaktiyarov.orderservice.event.OrderCreatedEvent;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandCallback;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.commandhandling.CommandResultMessage;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
@Saga
public class OrderSaga {

    @Autowired
    private transient CommandGateway commandGateway;

    @Autowired
    private transient QueryGateway queryGateway;


    @StartSaga
    @SagaEventHandler(associationProperty = "orderId")
    public void handle(OrderCreatedEvent orderCreatedEvent) {
        ReserveProductCommand reserveProductCommand = ReserveProductCommand.builder()
                .orderId(orderCreatedEvent.getOrderId())
                .productId(orderCreatedEvent.getProductId())
                .quantity(orderCreatedEvent.getQuantity())
                .userId(orderCreatedEvent.getUserId())
                .build();
        log.info("OrderCreatedEvent for orderId: " + reserveProductCommand.getOrderId());

        commandGateway.send(reserveProductCommand, new CommandCallback<ReserveProductCommand, Object>() {
            @Override
            public void onResult(CommandMessage<? extends ReserveProductCommand> commandMessage, CommandResultMessage<?> commandResultMessage) {
                if (commandResultMessage.isExceptional()) {

                }
            }
        });
    }

    @SagaEventHandler(associationProperty = "orderId")
    public void handle(ProductReservedEvent productReservedEvent) {
        FetchUserPaymentDetailsQuery fetchUserPaymentDetailsQuery =
                new FetchUserPaymentDetailsQuery(productReservedEvent.getUserId());
        User userPaymentDetails = null;
        try {
            userPaymentDetails = queryGateway.query(fetchUserPaymentDetailsQuery,
                    ResponseTypes.instanceOf(User.class)).join();
            log.info("ProductReservedEvent is called for orderId: " + productReservedEvent.getOrderId());

        } catch (Exception e) {
            // start compensating transaction
            log.error(e.getMessage());
        }
        if (userPaymentDetails == null) {
            // start compensating transaction
        }

        ProcessPaymentCommand processPaymentCommand = ProcessPaymentCommand.builder()
                .orderId(productReservedEvent.getOrderId())
                .paymentDetails(userPaymentDetails.getPaymentDetails())
                .paymentId(UUID.randomUUID().toString()).build();

        String result = null;
        try {
            commandGateway.sendAndWait(processPaymentCommand, 10, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        if (result == null) {
            log.info("The ProcessPaymentCommand is null");
        }
    }

    @SagaEventHandler(associationProperty = "orderId")
    public void handle(PaymentProcessedEvent paymentProcessedEvent) {
        ApproveOrderCommand approveOrderCommand = new ApproveOrderCommand(paymentProcessedEvent.getOrderId());
        commandGateway.send(approveOrderCommand);
    }

    @EndSaga
    @SagaEventHandler(associationProperty = "orderId")
    public void handle(OrderApprovedEvent orderApprovedEvent) {
        log.info("Order approved " + orderApprovedEvent.getOrderId());
    }

}
