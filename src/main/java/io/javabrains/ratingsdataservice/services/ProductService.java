package io.javabrains.ratingsdataservice.services;

import io.javabrains.ratingsdataservice.models.Product;
import io.javabrains.ratingsdataservice.repository.ProductRepository;
import io.javabrains.ratingsdataservice.repository.SupplierRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository, SupplierRepository supplierRepository) {
        this.productRepository = productRepository;
    }


    public void add(Product product) {
        if (product.getId() != null) { // verificar que el id sea null, si no es null, retornar excepcion
            throw new IllegalArgumentException("id must be null");
        }

        if (LocalDate.now().isAfter(product.getExpirationDate())) {
            throw new IllegalArgumentException("The product is expired");
        }

        if (product.getPrice() <= 0) { // verificar que el price sea mayor a cero, si no es mayor a cero, retornar excepcion
            throw new IllegalArgumentException("The price of the product cannot be 0!");
        }
        if (product.getSupplier() == null) {
            throw new IllegalArgumentException("Supplier does not exist");
        }
        productRepository.save(product);
        System.out.println("El producto fue agregado correctamente!");
    }

    public void modify(Product product) {
        if (product.getId() == null) {
            throw new IllegalArgumentException("id cannot be null");
        }

        if (product.getPrice() <= 0) {
            throw new IllegalArgumentException("The price of the product cannot be 0!");
        }

        if (LocalDate.now().isAfter(product.getExpirationDate())) {
            System.out.println("The product is expired");
        }
        if (product.getSupplier() == null) {
            throw new IllegalArgumentException("Supplier does not exist");
        }

        productRepository.save(product);
    }

    public void delete(Integer id) {
        if (productRepository.findById(id).isEmpty()) {
            throw new IllegalArgumentException("The product does not exist");
        }
        productRepository.deleteById(id);
    }

    public boolean existFor(int id) {

        return productRepository.findById(id).isPresent();
    }

    public Optional<Product> getProduct(Integer id) {

        return productRepository.findById(id);
    }

    public boolean existProduct(Integer id) {
        return getProduct(id).isPresent();
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

}
