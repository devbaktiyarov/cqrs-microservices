package com.arsenbaktiyarov.productservice.controller;

import com.arsenbaktiyarov.productservice.command.CreateProductCommand;
import com.arsenbaktiyarov.productservice.model.Product;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final Environment environment;
    private final CommandGateway commandGateway;

    public ProductController(Environment environment, CommandGateway commandGateway) {
        this.environment = environment;
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public String createProduct(@RequestBody Product product) {

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

    @GetMapping
    public String getProduct() {
        return "HTTP GET handled";
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
