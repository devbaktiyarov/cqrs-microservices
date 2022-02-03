package com.arsenbaktiyarov.productservice;

import com.arsenbaktiyarov.productservice.command.interceptor.CreateProductCommandInterceptor;
import com.arsenbaktiyarov.productservice.errorhandling.ProductServiceEventsErrorHandler;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.config.Configuration;
import org.axonframework.config.EventProcessingConfigurer;
import org.axonframework.eventhandling.ListenerInvocationErrorHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;

import java.util.function.Function;

@EnableDiscoveryClient
@SpringBootApplication
public class ProductServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }

    @Autowired
    public void registerCreateProductCommandInterceptor(ApplicationContext context,
                                                        CommandBus commandBus) {
		commandBus.registerDispatchInterceptor(context.getBean(CreateProductCommandInterceptor.class));

    }

    @Autowired
    public void configure(EventProcessingConfigurer configurer) {
           configurer.registerListenerInvocationErrorHandler("product-group",
                   cong -> new ProductServiceEventsErrorHandler());
    }
}
