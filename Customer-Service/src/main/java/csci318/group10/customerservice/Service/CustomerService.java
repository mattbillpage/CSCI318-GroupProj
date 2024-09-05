package csci318.group10.customerservice.Service;

import csci318.group10.customerservice.Model.Customer;
import csci318.group10.customerservice.Repository.CustomerRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service // what does this do
public class CustomerService {

  private final CustomerRepository customerRepository;
  private final RestTemplate restTemplate;

  public CustomerService(CustomerRepository customerRepository,
                         RestTemplate restTemplate) {
    this.customerRepository = customerRepository;
    this.restTemplate = restTemplate;
  }

  public List<Customer> getAllCustomers() {
    return customerRepository.findAll();
  }

  public Customer getCustomerById(int id) {
    return customerRepository.findById(id).orElse(null);
  }

  public Customer addCustomer(Customer newCustomer) {
    return customerRepository.save(newCustomer);
  }
}
