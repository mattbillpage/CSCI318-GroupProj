package csci318.group10.customerservice.infrastructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import csci318.group10.customerservice.domain.models.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
