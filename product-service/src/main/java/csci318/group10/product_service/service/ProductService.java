package csci318.group10.product_service.service;

import csci318.group10.product_service.domain.models.Product;
import csci318.group10.product_service.infrastructure.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final RestTemplate restTemplate;

    public ProductService(ProductRepository productRepository, RestTemplate restTemplate) {
        this.productRepository = productRepository;
        this.restTemplate = restTemplate;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(int id) {
        return productRepository.findById(id).orElse(null);
    }

    public void addProduct(Product product) {
        productRepository.save(product);
    }

    public void updateProductDiscount(int id, double discount) {
        Product product = productRepository.findById(id).orElse(null);
        if (product != null) {
            product.setPrice(product.getPrice() * discount);
            productRepository.save(product);
        }
    }

    public void reduceProductQuantity(int id, int quantity) {
        Product product = productRepository.findById(id).orElse(null);
        if (product != null) {
            product.setStockQuantity(product.getStockQuantity() - quantity);
            productRepository.save(product);
        }
    }
}
