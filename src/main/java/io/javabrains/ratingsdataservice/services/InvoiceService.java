package io.javabrains.ratingsdataservice.services;

import io.javabrains.ratingsdataservice.models.Invoice;
import io.javabrains.ratingsdataservice.models.InvoiceDetail;
import io.javabrains.ratingsdataservice.repository.CustomerRepository;
import io.javabrains.ratingsdataservice.repository.InvoiceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
        if (invoiceRepository.getInvoice(invoice.getId()) == null) {
            throw new IllegalArgumentException("The invoice does not exist");
        }
        validateInvoice(invoice);
        invoice.setDateTime(LocalDateTime.now());
        invoiceRepository.save(invoice);
    }

    public void deleteInvoice(int id) {
        if (invoiceRepository.getInvoice(id) == null) {
            throw new IllegalArgumentException("The invoice does not exist");
        }
        invoiceRepository.delete(id);
    }

    public Invoice getInvoice(int id) {

        return invoiceRepository.getInvoice(id);
    }

    private void validateInvoice(Invoice invoice) {
        if (LocalDate.now().isAfter(invoice.getExpiredDate())) {
            throw new IllegalArgumentException("The invoice is expired");
        }
        if (customerRepository.getCustomer(invoice.getCustomer().getId()) == null) {
            throw new IllegalArgumentException("The customer does not exist");
        }
        for(InvoiceDetail invoiceDetail : invoice.getInvoiceDetails()){
            invoiceDetailService.validateInvoiceDetails(invoiceDetail);
        }
    }
}
