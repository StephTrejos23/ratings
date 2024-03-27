package io.javabrains.ratingsdataservice.models;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @Column(name = "name", length = 50)
  private String name;
  @Column(name = "expiration_date")
  private LocalDate expirationDate;
  @Column(name = "price")
  private float price;
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "supplier_id")
  private Supplier supplier;

  public Product(Integer productId) {
  }

  public Product() {

  }

  public Product(Integer id, String name, LocalDate expirationDate, float price) {
    this.id = id;
    this.name = name;
    this.expirationDate = expirationDate;
    this.price = price;
  }

  public Product(Integer id, String name, LocalDate expirationDate, float price, Supplier supplier) {
    this.id = id;
    this.name = name;
    this.expirationDate = expirationDate;
    this.price = price;
    this.supplier = supplier;
  }

  public Supplier getSupplier() {
    return supplier;
  }

  public void setSupplier(Supplier supplier) {
    this.supplier = supplier;
  }

  public Product(Supplier supplier) {
    this.supplier = supplier;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public LocalDate getExpirationDate() {
    return expirationDate;
  }

  public void setExpirationDate(LocalDate expirationDate) {
    this.expirationDate = expirationDate;
  }

  public float getPrice() {
    return price;
  }

  public void setPrice(float price) {
    this.price = price;
  }


}
