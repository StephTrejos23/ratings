package io.javabrains.ratingsdataservice.services;

import io.javabrains.ratingsdataservice.models.Customer;
import io.javabrains.ratingsdataservice.models.InvoiceDetail;
import io.javabrains.ratingsdataservice.models.Product;
import io.javabrains.ratingsdataservice.models.Supplier;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class InvoiceDetailServiceTest {
    @Mock
    private ProductService productService;
    @InjectMocks
    private InvoiceDetailService invoiceDetailService;

    @Test
    void validatePriceThrowException(){
        InvoiceDetail invoiceDetail= new InvoiceDetail();
        final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> invoiceDetailService.validateInvoiceDetails(invoiceDetail));
        assertEquals("The price cannot be 0!", exception.getMessage());

    }

    @Test
    void validateAmountLessThan0ThrowException(){
        InvoiceDetail invoiceDetail= new InvoiceDetail();
        invoiceDetail.setPrice(4500);
        final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> invoiceDetailService.validateInvoiceDetails(invoiceDetail));
        assertEquals("The amount cannot be 0!", exception.getMessage());

    }

    @Test
    void productDoesNotExistThrowException(){
        InvoiceDetail invoiceDetail= new InvoiceDetail();
        Product product = new Product(1, "Lizano", LocalDate.now().plusDays(1), 4500);
        invoiceDetail.setPrice(4500);
        invoiceDetail.setAmount(2);
        invoiceDetail.setProduct(product);
        final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> invoiceDetailService.validateInvoiceDetails(invoiceDetail));
        assertEquals("The product does not exist", exception.getMessage());

    }
}
