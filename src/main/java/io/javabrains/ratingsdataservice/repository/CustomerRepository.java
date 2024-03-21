package io.javabrains.ratingsdataservice.repository;

import io.javabrains.ratingsdataservice.models.Customer;
import io.javabrains.ratingsdataservice.models.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

//    Customer getCustomer (int id);
//    void save(Customer customer);
//    void delete(int id);
}
