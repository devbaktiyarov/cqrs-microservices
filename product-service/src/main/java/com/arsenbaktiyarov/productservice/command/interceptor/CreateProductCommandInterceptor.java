package com.arsenbaktiyarov.productservice.command.interceptor;

import com.arsenbaktiyarov.productservice.command.CreateProductCommand;
import com.arsenbaktiyarov.productservice.model.data.ProductLookupEntity;
import com.arsenbaktiyarov.productservice.repository.ProductLookupRepository;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.BiFunction;

@Component
public class CreateProductCommandInterceptor
        implements MessageDispatchInterceptor<CommandMessage<?>> {

    private final ProductLookupRepository productLookupRepository;

    public CreateProductCommandInterceptor(ProductLookupRepository productLookupRepository) {
        this.productLookupRepository = productLookupRepository;
    }

    @Override
    public BiFunction<Integer, CommandMessage<?>,
            CommandMessage<?>> handle(List<? extends CommandMessage<?>> list) {
        return (index, command) -> {
            if (CreateProductCommand.class.equals(command.getPayloadType())) {

                CreateProductCommand createProductCommand = (CreateProductCommand) command.getPayload();
                ProductLookupEntity productLookupEntity = productLookupRepository
                        .findByProductIdOrTitle(createProductCommand.getProductId(),
                                createProductCommand.getTitle());
                if (productLookupEntity != null) {
                    throw new IllegalStateException(
                            String.format("Product with productId %s or title %s already exists",
                                    createProductCommand.getProductId(), createProductCommand.getTitle())
                    );
                }

            }
            return command;
        };
    }
}
