package com.arsenbaktiyarov.core.command;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Builder
@Data
public class ReserveProductCommand {

    @TargetAggregateIdentifier
    private String productId;
    private Integer quantity;
    private String orderId;
    private String userId;

}
