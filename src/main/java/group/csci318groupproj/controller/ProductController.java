package group.csci318groupproj.controller;

import group.csci318groupproj.model.Product;
import group.csci318groupproj.repository.ProductRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private final ProductRepository productRepository;

    ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @PostMapping("/products")
    Product createProduct(@RequestBody Product newProduct) {
        return productRepository.save(newProduct);
    }



}
