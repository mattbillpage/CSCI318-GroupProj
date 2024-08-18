package group.csci318groupproj.controller;

import group.csci318groupproj.model.Product;
import group.csci318groupproj.repository.ProductRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//API Controller for Product Class
@RestController
public class ProductController {

    private final ProductRepository productRepository;

    //Constructor injecting the ProductRepository into the ProductController
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    //Get all products from the database
    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    //Get a single product from the database by ID
    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable int id) {
        return productRepository.findById(id).orElse(null); //Return null if product not found under given ID
    }

    //Update an existing product in the database
    @PutMapping("/products/{id}")
    public Product updateProduct(@PathVariable int id, @RequestBody Product updatedProduct) {
        Product existingProduct = productRepository.findById(id).orElse(null);
        if (existingProduct != null) {
            existingProduct.setName(updatedProduct.getName());
            existingProduct.setDescription(updatedProduct.getDescription());
            existingProduct.setPrice(updatedProduct.getPrice());
            return productRepository.save(existingProduct);
        }
        return null; //Return null if product not found under given ID
    }

    //Add a new product to the database
    @PostMapping("/products")
    public Product createProduct(@RequestBody Product newProduct) {
        return productRepository.save(newProduct);
    }
}
