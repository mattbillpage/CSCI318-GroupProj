package csci318.group10.customerservice.service;

import csci318.group10.customerservice.infrastructure.repositories.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import csci318.group10.customerservice.domain.models.Customer;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;

    public CustomerService(CustomerRepository customerRepository, RestTemplate restTemplate) {
        this.customerRepository = customerRepository;
        this.restTemplate = restTemplate;
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(int id) {
        return customerRepository.findById(id).orElse(null);
    }


}
