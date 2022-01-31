package com.arsenbaktiyarov.productservice.query;

import com.arsenbaktiyarov.productservice.model.Product;
import com.arsenbaktiyarov.productservice.model.ProductRestModel;
import com.arsenbaktiyarov.productservice.model.data.ProductEntity;
import com.arsenbaktiyarov.productservice.repository.ProductRepository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductQueryHandler {

    private final ProductRepository productRepository;

    public ProductQueryHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @QueryHandler
    public List<ProductRestModel> findProducts(FindProductsQuery query) {
        List<ProductRestModel> products = new ArrayList<>();
        List<ProductEntity> productEntityList = productRepository.findAll();
        for (ProductEntity productEntity : productEntityList) {
            ProductRestModel productRestModel = new ProductRestModel();
            BeanUtils.copyProperties(productEntity, productRestModel);
            products.add(productRestModel);
        }
        return products;

    }
}
