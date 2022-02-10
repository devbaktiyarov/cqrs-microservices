package com.arsenbaktiyarov.paymentservice.repository;

import com.arsenbaktiyarov.paymentservice.model.data.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<PaymentEntity, String> {
}
