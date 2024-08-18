package group.csci318groupproj.repository;

import group.csci318groupproj.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;


//Interface that extends the JPA library for H2 functionality for Customer Class
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
