package com.arsenbaktiyarov.productservice.model.data;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "product")
public class ProductEntity implements Serializable {


    private static final long serialVersionUID = -4095330650806019086L;

    @Id
//    @Column(unique = true)
    private String productId;

    @Column(unique = true)
    private String title;
    private BigDecimal price;
    private Integer quantity;
}
