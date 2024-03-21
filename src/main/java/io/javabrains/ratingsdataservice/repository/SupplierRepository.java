package io.javabrains.ratingsdataservice.repository;


import io.javabrains.ratingsdataservice.models.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
//    Supplier getSupplier (int id);
//    void save(Supplier supplier);
//    void delete(int id);
}
