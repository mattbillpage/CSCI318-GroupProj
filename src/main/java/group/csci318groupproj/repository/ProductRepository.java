package group.csci318groupproj.repository;

import group.csci318groupproj.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
