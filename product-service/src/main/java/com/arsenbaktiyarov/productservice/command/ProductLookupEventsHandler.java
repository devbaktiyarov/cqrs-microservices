package com.arsenbaktiyarov.productservice.command;

import com.arsenbaktiyarov.productservice.event.ProductCreatedEvent;
import com.arsenbaktiyarov.productservice.model.data.ProductLookupEntity;
import com.arsenbaktiyarov.productservice.repository.ProductLookupRepository;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("product-group")
public class ProductLookupEventsHandler {

    private final ProductLookupRepository productLookupRepository;

    public ProductLookupEventsHandler(ProductLookupRepository productLookupRepository) {
        this.productLookupRepository = productLookupRepository;
    }

    @EventHandler
    public void on(ProductCreatedEvent event) {
        ProductLookupEntity productLookupEntity = new ProductLookupEntity(event.getProductId(),
                event.getTitle());

        productLookupRepository.save(productLookupEntity);
    }
}
