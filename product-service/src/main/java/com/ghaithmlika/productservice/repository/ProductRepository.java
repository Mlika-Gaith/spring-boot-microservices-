package com.ghaithmlika.productservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ghaithmlika.productservice.model.Product;

public interface ProductRepository extends MongoRepository<Product, String> {
    
    
}
