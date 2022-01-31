package com.arsenbaktiyarov.productservice.model;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Data
public class Product {

    @NotBlank(message = "Product title is a required field")
    private String title;

    @Min(value = 1, message = "Price can't be lower than 1")
    private BigDecimal price;

    @Min(value = 1, message = "Quantity can't be lower than 1")
    @Max(value = 10, message = "Quantity can't be higher than 10")
    private Integer quantity;


}
