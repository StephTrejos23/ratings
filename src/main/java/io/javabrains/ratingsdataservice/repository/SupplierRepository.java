package io.javabrains.ratingsdataservice.repository;

import io.javabrains.ratingsdataservice.models.Customer;
import io.javabrains.ratingsdataservice.models.Supplier;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository {
    Supplier getSupplier (int id);
    void save(Supplier supplier);
    void delete(int id);
}
