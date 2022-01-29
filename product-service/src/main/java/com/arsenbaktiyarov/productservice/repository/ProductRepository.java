package com.arsenbaktiyarov.productservice.repository;

import com.arsenbaktiyarov.productservice.model.data.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, String> {
    ProductEntity findByProductId(String productId);
    ProductEntity findByProductIdOrTitle(String productId, String title);

}
