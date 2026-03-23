package com.exam.springExam.service;

import com.exam.springExam.q1.Product;
import com.exam.springExam.q2.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    // Get all products
    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    // Get product by ID
    public Product getProductById(Long id) {
        return repository.findById(id).orElse(null);
    }

    // Save product with business logic
    public Product saveProduct(Product product) {
        // Example business rules:
        if (product.getPrice() == null || product.getPrice() <= 0) {
            throw new IllegalArgumentException("Price must be greater than zero");
        }
        if (product.getName() == null || product.getName().isBlank()) {
            throw new IllegalArgumentException("Product name is required");
        }
        if (product.getCategory() == null || product.getCategory().isBlank()) {
            throw new IllegalArgumentException("Category is required");
        }

        return repository.save(product);
    }
    public List<Product> findByCategory(String category) {
        return repository.findByCategory(category);
    }

    // Find products cheaper than a given price
    public List<Product> findByPriceLessThan(Double price) {
        return repository.findByPriceLessThan(price);
    }


    // Delete product by ID
    public void deleteProduct(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Product with ID " + id + " does not exist");
        }
        repository.deleteById(id);
    }
}