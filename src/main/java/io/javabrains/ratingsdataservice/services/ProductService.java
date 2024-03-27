package io.javabrains.ratingsdataservice.services;

import io.javabrains.ratingsdataservice.exception.ResourceNotFoundException;
import io.javabrains.ratingsdataservice.models.Product;
import io.javabrains.ratingsdataservice.models.Supplier;
import io.javabrains.ratingsdataservice.repository.ProductRepository;
import io.javabrains.ratingsdataservice.repository.SupplierRepository;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
  private final ProductRepository productRepository;
  private final SupplierRepository supplierRepository;

  public ProductService(ProductRepository productRepository, SupplierRepository supplierRepository) {
    this.productRepository = productRepository;
    this.supplierRepository = supplierRepository;
  }

  public void add(Product product) {
    if (product.getId() != 0) {
      throw new IllegalArgumentException("Id must be 0.");
    }

    validateProduct(product);

    productRepository.save(product);
    System.out.println("El producto fue agregado correctamente!");
  }

  public void modify(Product product) {
    if (!existProduct((product.getId()))) {
      throw new ResourceNotFoundException("The product does not exist");
    }

    validateProduct(product);
    productRepository.save(product);
    System.out.println("El producto fue modificado correctamente!");
  }

  public void delete(Integer id) {
    if (!existProduct(id)) {
      throw new ResourceNotFoundException("The product does not exist");
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


  private void validateProduct(Product product) {
    if (StringUtils.isBlank(product.getName())) {
      throw new IllegalArgumentException("Name cannot be null or blank");
    }

    if (LocalDate.now().isAfter(product.getExpirationDate())) {
      throw new IllegalArgumentException("The product is expired");
    }

    if (product.getPrice() <= 0) { // verificar que el price sea mayor a cero, si no es mayor a cero, retornar excepcion
      throw new IllegalArgumentException("The price of the product cannot be 0!");
    }

    if (product.getSupplier() == null) {
      throw new IllegalArgumentException("Supplier missing");
    }

    product.setSupplier(supplierRepository.findById(product.getSupplier().getId())
        .orElseThrow(() -> new ResourceNotFoundException("Supplier does not exist")));
  }
}
