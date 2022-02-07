package com.arsenbaktiyarov.core.event;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProductReservedEvent {
    private String productId;
    private Integer quantity;
    private String orderId;
    private String userId;
}
