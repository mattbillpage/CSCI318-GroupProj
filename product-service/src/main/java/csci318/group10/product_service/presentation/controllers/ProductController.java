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
}
