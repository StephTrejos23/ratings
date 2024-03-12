package io.javabrains.ratingsdataservice.models;

public class InvoiceDetail {
    private int id;
    private Invoice invoice;
    private Product product;
    private float price;
    private int amount;
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
