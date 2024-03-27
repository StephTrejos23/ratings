package io.javabrains.ratingsdataservice.repository;

import io.javabrains.ratingsdataservice.models.Product;
import io.javabrains.ratingsdataservice.models.Rating;
import io.javabrains.ratingsdataservice.models.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
  Optional<Object> findByName(String productName);
//    Product getProduct(Integer id);
//    void save(Product product);
//    void delete(Integer id);
}
