package csci318.group10.product_service.infrastructure.repositories;

import csci318.group10.product_service.domain.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
