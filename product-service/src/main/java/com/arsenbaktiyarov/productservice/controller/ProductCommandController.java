package com.arsenbaktiyarov.productservice.controller;

import com.arsenbaktiyarov.productservice.command.CreateProductCommand;
import com.arsenbaktiyarov.productservice.model.Product;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductCommandController {

    private final Environment environment;
    private final CommandGateway commandGateway;

    public ProductCommandController(Environment environment, CommandGateway commandGateway) {
        this.environment = environment;
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public String createProduct(@Valid @RequestBody Product product) {

        CreateProductCommand createProductCommand
                = CreateProductCommand.builder()
                .title(product.getTitle())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .productId(UUID.randomUUID().toString())
                .build();

        String returnedValue;
        
        try {
            returnedValue = commandGateway.sendAndWait(createProductCommand);
        } catch (Exception e) {
            returnedValue = e.getLocalizedMessage();
        }

        return returnedValue;

    }

    @PutMapping
    public String updateProduct() {
        return "HTTP PUT handled";
    }

    @DeleteMapping
    public String deleteProduct() {
        return "HTTP DELETE handled";
    }


}
