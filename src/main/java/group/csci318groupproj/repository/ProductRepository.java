package group.csci318groupproj.repository;

import group.csci318groupproj.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;


//Interface that extends the JPA library for H2 functionality for Product Class
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
