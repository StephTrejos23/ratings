package io.javabrains.ratingsdataservice.services;

import io.javabrains.ratingsdataservice.models.Customer;
import io.javabrains.ratingsdataservice.repository.CustomerRepository;
import org.springframework.stereotype.Service;

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
        if (customerRepository.getCustomer(customer.getId()) == null) {
            throw new IllegalArgumentException("The customer does not exist");
        }
        validateCustomer(customer);
        customerRepository.save(customer);

    }

    public void deleteCustomer(int id) {
        if (customerRepository.getCustomer(id) == null) {
            throw new IllegalArgumentException("The customer does not exist");
        }
        customerRepository.delete(id);
    }

    public Customer getCustomer(int id) {

        return customerRepository.getCustomer(id);
    }

    private static void validateCustomer(Customer customer) {
    if (customer.getEmail()!= null && !customer.getEmail().contains("@")) {
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
}

