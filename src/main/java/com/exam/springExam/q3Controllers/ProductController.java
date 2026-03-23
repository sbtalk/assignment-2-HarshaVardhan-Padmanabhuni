package com.exam.springExam.q3Controllers;

import com.exam.springExam.q1.Product;
import com.exam.springExam.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        return product != null ? ResponseEntity.ok(product) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Product saved = productService.saveProduct(product);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping("/category/{category}")
    public List<Product> getProductsByCategory(@PathVariable String category) {
        return productService.findByCategory(category);
    }

    @GetMapping("/cheaper/{price}")
    public List<Product> getProductsCheaperThan(@PathVariable Double price) {
        return productService.findByPriceLessThan(price);
    }
}