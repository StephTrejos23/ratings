package io.javabrains.ratingsdataservice.repository;

import io.javabrains.ratingsdataservice.models.Invoice;
import io.javabrains.ratingsdataservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
//    Invoice getInvoice (int id);
//    void save(Invoice invoice);
//    void delete(int id);
}
