package com.arsenbaktiyarov.productservice.event;

import com.arsenbaktiyarov.productservice.model.data.ProductEntity;
import com.arsenbaktiyarov.productservice.repository.ProductRepository;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("product-group")
public class ProductEventsHandler {

    private final ProductRepository productRepository;

    public ProductEventsHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @ExceptionHandler(resultType = Exception.class)
    public void handle(Exception exception) {

    }
    @ExceptionHandler(resultType = IllegalArgumentException.class)
    public void handle(IllegalArgumentException exception) {

    }

    @EventHandler
    public void on(ProductCreatedEvent productCreatedEvent) throws Exception {
        ProductEntity productEntity = new ProductEntity();
        BeanUtils.copyProperties(productCreatedEvent, productEntity);
        try {
            productRepository.save(productEntity);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        throw new Exception("Forcing exception");
    }
}
