package io.javabrains.ratingsdataservice.repository;

import io.javabrains.ratingsdataservice.models.Product;
import io.javabrains.ratingsdataservice.models.Rating;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository {
    Product getProduct(Integer id);
    void save(Product product);
    void delete(Integer id);
}
