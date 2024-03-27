package io.javabrains.ratingsdataservice.models;

import jakarta.persistence.*;

@Entity
public class InvoiceDetail {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) // agrega un id secuencial automatico
  private int id;
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "invoice_id")
  private Invoice invoice;
  @ManyToOne(fetch = FetchType.EAGER)
  private Product product;
  @Column(name = "price")
  private float price;
  @Column(name = "amount")
  private int amount;
  @Column(name = "taxes")
  private float taxes;

  public InvoiceDetail() {
  }

  public InvoiceDetail(int id, Invoice invoice, Product product, float price, int amount, float taxes) {
    this.id = id;
    this.invoice = invoice;
    this.product = product;
    this.price = price;
    this.amount = amount;
    this.taxes = taxes;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Invoice getInvoice() {
    return invoice;
  }

  public void setInvoice(Invoice invoice) {
    this.invoice = invoice;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public float getPrice() {
    return price;
  }

  public void setPrice(float price) {
    this.price = price;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  public float getTaxes() {
    return taxes;
  }

  public void setTaxes(float taxes) {
    this.taxes = taxes;
  }
}
