package com.arsenbaktiyarov.paymentservice.event;

import com.arsenbaktiyarov.core.command.PaymentProcessedEvent;
import com.arsenbaktiyarov.paymentservice.model.data.PaymentEntity;
import com.arsenbaktiyarov.paymentservice.repository.PaymentRepository;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PaymentEventsHandler {
    private final PaymentRepository paymentRepository;

    public PaymentEventsHandler(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @EventHandler
    public void on(PaymentProcessedEvent paymentProcessedEvent) {
        PaymentEntity paymentEntity = new PaymentEntity();
        BeanUtils.copyProperties(paymentProcessedEvent, paymentEntity);
        paymentRepository.save(paymentEntity);
    }

}
