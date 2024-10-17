package csci318.group10.product_service.service;

import csci318.group10.product_service.domain.models.Product;
import csci318.group10.product_service.infrastructure.repositories.ProductRepository;
import csci318.group10.product_service.shareddomain.events.ProductEvent;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final RestTemplate restTemplate;
    private final StreamBridge streamBridge;

    public ProductService(ProductRepository productRepository, RestTemplate restTemplate, StreamBridge streamBridge) {
        this.productRepository = productRepository;
        this.restTemplate = restTemplate;
        this.streamBridge = streamBridge;
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
            updateProduct(product);
        }
    }

    @TransactionalEventListener
    public void updateProduct(Product product) {
        ProductEvent event = new ProductEvent(product.getID(), product.getStockQuantity());
        streamBridge.send("productEventsChannel", event);
    }
}
