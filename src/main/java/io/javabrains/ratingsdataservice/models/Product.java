package io.javabrains.ratingsdataservice.models;

import java.time.LocalDate;

public class Product {
    private Integer id;
    private String name;
    private LocalDate expirationDate;
    private float price;
    private Supplier supplier;

    public Product(Integer productId) {
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

    public Product(Integer id, String name, LocalDate expirationDate, float price) {
        this.id = id;
        this.name = name;
        this.expirationDate = expirationDate;
        this.price = price;
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
