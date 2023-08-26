package com.ghaithmlika.productservice.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ghaithmlika.productservice.dto.ProductRequest;
import com.ghaithmlika.productservice.dto.ProductResponse;
import com.ghaithmlika.productservice.model.Product;
import com.ghaithmlika.productservice.repository.ProductRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepo;
    public void addProduct(ProductRequest productRequest) {
        Product product = Product.builder().name(productRequest.getName()).description(productRequest.getDescription())
                .price(productRequest.getPrice()).build();
        productRepo.save(product);
        log.info("Product {} is saved", product.getId());

    }

    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepo.findAll();
        return products.stream().map(this::mapToProductResponse).toList();
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder().id(product.getId()).name(product.getName())
                .description(product.getDescription()).price(product.getPrice()).build();
    }

}
