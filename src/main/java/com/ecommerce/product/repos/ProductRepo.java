package com.ecommerce.product.repos;

import com.ecommerce.product.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductRepo extends MongoRepository<Product,String> {
    List<Product> findByDeletedFalse(); // active products
    List<Product> findByDeletedTrue();
}
