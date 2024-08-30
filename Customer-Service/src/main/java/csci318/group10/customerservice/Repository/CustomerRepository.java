package csci318.group10.customerservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import csci318.group10.customerservice.Model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
