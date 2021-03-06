package com.arsenbaktiyarov.productservice.model;

import lombok.Data;

import javax.persistence.Column;
import java.math.BigDecimal;

@Data
public class ProductRestModel {
    private String productId;
    private String title;
    private BigDecimal price;
    private Integer quantity;
}
