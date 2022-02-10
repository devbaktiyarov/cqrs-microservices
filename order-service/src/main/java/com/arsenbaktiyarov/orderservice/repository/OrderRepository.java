package com.arsenbaktiyarov.orderservice.repository;

import com.arsenbaktiyarov.orderservice.model.data.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity,String> {
    OrderEntity findByOrderId(String orderId);
}
