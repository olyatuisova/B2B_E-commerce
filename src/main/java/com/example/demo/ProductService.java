package com.example.demo;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product save(Product product) {
        if (product.getBasePrice() < 0) {
            throw new IllegalArgumentException("price cant be negative :("); // Для негативного тесту
        }
        return productRepository.save(product);
    }
}