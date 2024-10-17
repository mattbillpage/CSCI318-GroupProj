package csci318.group10.customerservice.presentation.controllers;


import csci318.group10.customerservice.domain.models.Address;
import csci318.group10.customerservice.domain.models.Customer;
import csci318.group10.customerservice.service.CustomerService;
import org.springframework.http.ResponseEntity;
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
        return customerService.getCustomerWithAddress(id);
    }

    @PostMapping("/{id}/cart")
    public ResponseEntity<Void> addCartToUser(@PathVariable int id) {
        customerService.addCartToUser(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/create")
    void createCustomer(@RequestBody Customer customer) {
        customerService.createNewCustomer(customer);
    }

    @PutMapping("/{id}/address")
    public ResponseEntity<Void> updateCustomerAddress(@PathVariable int id, @RequestBody Address address) {
        customerService.updateCustomerAddress(id, address);
        return ResponseEntity.ok().build();
    }
}
