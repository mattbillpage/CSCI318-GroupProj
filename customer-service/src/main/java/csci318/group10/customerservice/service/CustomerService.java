package csci318.group10.customerservice.service;

import csci318.group10.customerservice.domain.models.Address;
import csci318.group10.customerservice.infrastructure.repositories.CustomerRepository;
import jakarta.transaction.Transactional;
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

    public Customer getCustomerWithAddress(int id) {
        return customerRepository.findById(id).orElse(null);
    }



    public void addCartToUser(int id) {
        Customer customer = customerRepository.findById(id).orElse(null);
        if (customer == null) {
            throw new RuntimeException("Customer not found");
        }

        String url = "http://localhost:8080/cart/users/" + id;
        String cartID = restTemplate.postForObject(url, null, String.class);
        customer.setCartID(Integer.parseInt(cartID));
        customerRepository.save(customer);
    }

    public void createNewCustomer(Customer customer) {
        customerRepository.save(customer);
    }


    public void updateCustomerAddress(int id, Address address) {
        Customer customer = customerRepository.findById(id).orElse(null);
        if (customer == null) {
            throw new RuntimeException("Customer not found");
        }
        customer.setAddress(address);
        customerRepository.save(customer);
    }
}
