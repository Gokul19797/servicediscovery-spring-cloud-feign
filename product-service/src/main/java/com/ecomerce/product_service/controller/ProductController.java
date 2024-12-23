package com.ecomerce.product_service.controller;


import com.ecomerce.product_service.entity.Product;
import com.ecomerce.product_service.repository.ProductRepository;
import jakarta.validation.Valid;
import jakarta.ws.rs.POST;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;



import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {


    @Autowired
    private ProductRepository productRepository;

    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) {
        Product savedProduct = productRepository.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productRepository.findAll();
        if (products.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(products);
    }


    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable Long productId) {
        return productRepository.findById(productId)
                .map(ResponseEntity::ok) // Return 200 OK with the product if found
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found")); // Throw 404 if not found
    }



}
