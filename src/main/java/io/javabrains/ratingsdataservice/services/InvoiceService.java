package io.javabrains.ratingsdataservice.services;

import io.javabrains.ratingsdataservice.models.Invoice;
import io.javabrains.ratingsdataservice.models.InvoiceDetail;
import io.javabrains.ratingsdataservice.repository.CustomerRepository;
import io.javabrains.ratingsdataservice.repository.InvoiceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final CustomerRepository customerRepository;
    private final InvoiceDetailService invoiceDetailService;


    public InvoiceService(InvoiceRepository invoiceRepository, CustomerRepository customerRepository, InvoiceDetailService invoiceDetailService, InvoiceDetailService invoiceDetailService1) {
        this.invoiceRepository = invoiceRepository;
        this.customerRepository = customerRepository;
        this.invoiceDetailService = invoiceDetailService1;
    }

    public void addInvoice(Invoice invoice) {
        validateInvoice(invoice);
        invoice.setDateTime(LocalDateTime.now());

//        Customer customer = invoice.getCustomer();
//        int customerId = customer.getId();
//        invoice.getCustomer().getId()

        invoiceRepository.save(invoice);


    }

    public void modifyInvoice(Invoice invoice) {
        if (!existInvoice(invoice.getId())) {
            throw new IllegalArgumentException("The invoice does not exist");
        }
        validateInvoice(invoice);
        invoice.setDateTime(LocalDateTime.now());
        invoiceRepository.save(invoice);
    }

    public void deleteInvoice(int id) {
        if (!existInvoice(id)) {
            throw new IllegalArgumentException("The invoice does not exist");
        }
        invoiceRepository.deleteById(id);
    }

    public Optional<Invoice> getInvoice(int id) {

        return invoiceRepository.findById(id);
    }

    public Optional<Invoice> getInvoice(Integer id) {
        return invoiceRepository.findById(id);
    }

    public boolean existInvoice(Integer id) {
        return getInvoice(id).isPresent();
    }

    public List<Invoice> getInvoices() {
        return invoiceRepository.findAll();
    }

    private void validateInvoice(Invoice invoice) {
        if (LocalDate.now().isAfter(invoice.getExpiredDate())) {
            throw new IllegalArgumentException("The invoice is expired");
        }
        if (customerRepository.findById(invoice.getCustomer().getId()).isEmpty()) {
            throw new IllegalArgumentException("The customer does not exist");
        }
        for (InvoiceDetail invoiceDetail : invoice.getInvoiceDetails()) {
            invoiceDetailService.validateInvoiceDetails(invoiceDetail);
        }
    }

}
