package io.javabrains.ratingsdataservice.repository;

import io.javabrains.ratingsdataservice.models.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Integer> {

  Optional<Supplier> findByName(String name);
}
