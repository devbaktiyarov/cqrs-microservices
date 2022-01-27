package com.arsenbaktiyarov.productservice.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Product {
    private String title;
    private BigDecimal price;
    private Integer quantity;


}
