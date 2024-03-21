package io.javabrains.ratingsdataservice.resources;

import io.javabrains.ratingsdataservice.models.Invoice;
import io.javabrains.ratingsdataservice.models.Supplier;
import io.javabrains.ratingsdataservice.services.InvoiceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping
public class InvoiceController {
    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping("/invoices")
    public List<Invoice> getInvoices() {
        return invoiceService.getInvoices();
    }

    @GetMapping("/invoice/{invoiceId}")
    public Invoice getInvoice(@PathVariable(value = "invoiceId") Integer id) {
        return invoiceService.getInvoice(id).orElse(null);
    }

    @PostMapping("/invoice")
    public void createInvoice(@RequestBody Invoice invoice) {
        invoiceService.addInvoice(invoice);
    }

    @PutMapping("/invoice/{invoiceId}")
    public void updateInvoice(@PathVariable(value = "invoiceId") Integer id, @RequestBody Invoice invoice) {
        invoice.setId(id);
        invoiceService.modifyInvoice(invoice);
    }

    @DeleteMapping("/invoice/{invoiceId}")
    public void deleteInvoice(@PathVariable(value = "invoiceId") Integer id) {
        invoiceService.deleteInvoice(id);
    }
}
