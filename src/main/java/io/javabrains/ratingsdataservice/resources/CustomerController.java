package io.javabrains.ratingsdataservice.resources;

import io.javabrains.ratingsdataservice.exception.ResourceNotFoundException;
import io.javabrains.ratingsdataservice.models.Customer;
import io.javabrains.ratingsdataservice.repository.CustomerRepository;
import io.javabrains.ratingsdataservice.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class CustomerController {

  private final CustomerService customerService;

  public CustomerController(CustomerService customerService) {
    this.customerService = customerService;
  }

  @GetMapping("/customers")
  public List<Customer> getCustomers() {
    return customerService.getCustomers();
  }

  @GetMapping("/customer/{customerId}")
  public Customer getCustomer(@PathVariable(value = "customerId") Integer id) {
    return customerService.getCustomer(id).orElseThrow(() -> new ResourceNotFoundException("The customer does not exist"));
  }

  @PostMapping("/customer")
  public ResponseEntity<Void> createCustomer(@RequestBody Customer customer) {
    customerService.addCustomer(customer);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @PutMapping("/customer/{customerId}")
  public void updateCustomer(@PathVariable(value = "customerId") Integer id, @RequestBody Customer customer) {
    customer.setId(id);
    customerService.modifyCustomer(customer);
  }

  @DeleteMapping("/customer/{customerId}")
  public void deleteCustomer(@PathVariable(value = "customerId") Integer id) {
    customerService.deleteCustomer(id);
  }
}
