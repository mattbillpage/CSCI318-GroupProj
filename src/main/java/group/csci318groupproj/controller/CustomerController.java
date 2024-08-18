package group.csci318groupproj.controller;

import group.csci318groupproj.model.Customer;
import group.csci318groupproj.repository.CustomerRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//API Controller for Customer Class
@RestController
public class CustomerController {

    private final CustomerRepository customerRepository;

    //Constructor injecting the CustomerRepository into the CustomerController
    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    //Get all customers from the database
    @GetMapping("/customer")
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    //Get a single customer from the database by ID
    @GetMapping("/customer/{id}")
    public Customer getCustomer(@PathVariable int id) {
        return customerRepository.findById(id).orElse(null); //Return null if customer not found under given ID
    }
    
    //Update an existing customer in the database (Not including password)
    @PutMapping("/customer/{id}")
    public Customer updateCustomer(@PathVariable int id, @RequestBody Customer updatedCustomer) {
        Customer existingCustomer = customerRepository.findById(id).orElse(null);
        if (existingCustomer != null) {
            existingCustomer.setName(updatedCustomer.getName());
            existingCustomer.setAddress(updatedCustomer.getAddress());
            existingCustomer.setEmailID(updatedCustomer.getEmailID());
            return customerRepository.save(existingCustomer);
            }
        return null; //Return null if customer not found under given ID
    }

    //Add a new customer to the database
    @PostMapping("/customer")
    public Customer addCustomer(@RequestBody Customer newCustomer) {
        return customerRepository.save(newCustomer);
    }
}
