package io.javabrains.ratingsdataservice.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Invoice {
    private int id;
    private Customer customer;
    private LocalDateTime dateTime;
    private LocalDate expiredDate;
    private List<InvoiceDetail> invoiceDetails;

    public Invoice() {
    }

    public Invoice(int id, Customer customer, LocalDateTime dateTime, LocalDate expiredDate) {
        this.id = id;
        this.customer = customer;
        this.dateTime = dateTime;
        this.expiredDate = expiredDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer() {

        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public LocalDate getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(LocalDate expiredDate) {
        this.expiredDate = expiredDate;
    }

    public List<InvoiceDetail> getInvoiceDetails() {
        return invoiceDetails;
    }

    public void setInvoiceDetails(List<InvoiceDetail> invoiceDetails) {
        this.invoiceDetails = invoiceDetails;
    }
}
