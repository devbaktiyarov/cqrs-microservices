package com.arsenbaktiyarov.orderservice.model;

import lombok.Data;

@Data
public class Order {
    private String productId;
    private Integer quantity;
    private String addressId;
}
