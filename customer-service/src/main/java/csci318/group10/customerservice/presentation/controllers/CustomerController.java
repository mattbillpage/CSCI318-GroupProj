package csci318.group10.customerservice.presentation.controllers;


import csci318.group10.customerservice.domain.models.Customer;
import csci318.group10.customerservice.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;

    CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/all")
    List<Customer> allCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    Customer getCustomer(@PathVariable int id) {
        return customerService.getCustomerById(id);
    }
}
