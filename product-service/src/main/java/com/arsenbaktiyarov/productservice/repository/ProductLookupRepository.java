package com.arsenbaktiyarov.productservice.repository;

import com.arsenbaktiyarov.productservice.model.data.ProductLookupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductLookupRepository extends JpaRepository<ProductLookupEntity,String> {
    ProductLookupEntity findByProductIdOrTitle(String productId, String title);
}
