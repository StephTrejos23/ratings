package io.javabrains.ratingsdataservice.repository;

import io.javabrains.ratingsdataservice.models.Invoice;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository {
    Invoice getInvoice (int id);
    void save(Invoice invoice);
    void delete(int id);
}
