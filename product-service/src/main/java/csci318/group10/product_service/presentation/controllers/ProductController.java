package csci318.group10.product_service.presentation.controllers;


import csci318.group10.product_service.service.ProductService;
import csci318.group10.product_service.domain.models.Product;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    List<Product> allProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    Product getProduct(@PathVariable int id) {
        return productService.getProductById(id);
    }

    @PostMapping("/create")
    void createProduct(@RequestBody Product product) {
        productService.addProduct(product);
    }

    @PutMapping("/{id}/discount/{discount}")
    void updateProductDiscount(@PathVariable int id, @PathVariable double discount) {
        productService.updateProductDiscount(id, discount);
    }

    @PutMapping("/{id}/reduceQuantity/{quantity}")
    void reduceProductQuantity(@PathVariable int id, @PathVariable int quantity) {
        productService.reduceProductQuantity(id, quantity);
    }
}
