package io.javabrains.ratingsdataservice.resources;

import io.javabrains.ratingsdataservice.exception.ResourceNotFoundException;
import io.javabrains.ratingsdataservice.models.Product;
import io.javabrains.ratingsdataservice.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class ProductController {
  private final ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping("/products")
  public List<Product> getProduct() {
    return productService.getProducts();
  }

  @GetMapping("/product/{productId}")
  public Product getProduct(@PathVariable(value = "productId") Integer id) {
    return productService.getProduct(id).orElseThrow(() -> new ResourceNotFoundException("The product does not exist"));
  }

  @PostMapping("/product")
  public ResponseEntity<Void> createProduct(@RequestBody Product product) {
    productService.add(product);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @PutMapping("/product/{productId}")
  public void updateProduct(@PathVariable(value = "productId") Integer id, @RequestBody Product product) {
    product.setId(id);
    productService.modify(product);
  }

  @DeleteMapping("/product/{productId}")
  public void deleteProduct(@PathVariable(value = "productId") Integer id) {
    productService.delete(id);
  }
}
