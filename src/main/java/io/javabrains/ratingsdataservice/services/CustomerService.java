package io.javabrains.ratingsdataservice.services;

import io.javabrains.ratingsdataservice.models.Customer;
import io.javabrains.ratingsdataservice.models.Product;
import io.javabrains.ratingsdataservice.models.Supplier;
import io.javabrains.ratingsdataservice.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void addCustomer(Customer customer) {
        validateCustomer(customer);
        customerRepository.save(customer);
    }

    public void modifyCustomer(Customer customer) {
        if (!existCustomer(customer.getId())) {
            throw new IllegalArgumentException("The customer does not exist");
        }
        validateCustomer(customer);
        customerRepository.save(customer);

    }

    public void deleteCustomer(int id) {
        if (!existCustomer(id)) {
            throw new IllegalArgumentException("The customer does not exist");
        }
        customerRepository.deleteById(id);
    }

    public Optional<Customer> getCustomer(int id) {
        return customerRepository.findById(id);
    }

    public Optional<Customer> getCustomer(Integer id) {

        return customerRepository.findById(id);
    }

    public boolean existCustomer(int id) {
        return getCustomer(id).isPresent();
    }

    public List<Customer> getCustomer() {
        return customerRepository.findAll();
    }

    private static void validateCustomer(Customer customer) {
        if (customer.getEmail() != null && !customer.getEmail().contains("@")) {
            throw new IllegalArgumentException("Email must contain an @.");
        }

        if (customer.getPhone() != null && (customer.getPhone().length() != 10 || !customer.getPhone().matches("\\d+"))) {// que pasa si le paso 123456789k
            throw new IllegalArgumentException("Phone must contain 10 digits.");
        }
    }

    public int plusOne(int value) {
        return value + 1;
    }

    public int division(int value1, int value2) {
        return value1 / value2;
    }

    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }
}

