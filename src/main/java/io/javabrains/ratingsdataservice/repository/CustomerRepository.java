package io.javabrains.ratingsdataservice.repository;

import io.javabrains.ratingsdataservice.models.Customer;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository {

    Customer getCustomer (int id);
    void save(Customer customer);
    void delete(int id);
}
