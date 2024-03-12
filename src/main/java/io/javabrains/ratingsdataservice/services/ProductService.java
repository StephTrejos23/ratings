package io.javabrains.ratingsdataservice.services;

import io.javabrains.ratingsdataservice.models.Product;
import io.javabrains.ratingsdataservice.repository.ProductRepository;
import io.javabrains.ratingsdataservice.repository.SupplierRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

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
        if(product.getSupplier() != null){
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
        if(product.getSupplier() != null){
            throw new IllegalArgumentException("Supplier does not exist");
        }

        productRepository.save(product);
    }

    public void delete(Integer id) {
        if (productRepository.getProduct(id) == null) {
            throw new IllegalArgumentException("The product does not exist");
        }
        productRepository.delete(id);
    }

    public Product get(Integer id) {

        return productRepository.getProduct(id);
    }

    public boolean existFor(int id) {

        return productRepository.getProduct(id) != null;
    }

}
