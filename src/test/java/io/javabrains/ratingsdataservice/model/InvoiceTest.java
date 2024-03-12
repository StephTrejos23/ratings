package io.javabrains.ratingsdataservice.model;

import io.javabrains.ratingsdataservice.models.Customer;
import io.javabrains.ratingsdataservice.models.Invoice;
import io.javabrains.ratingsdataservice.models.InvoiceDetail;
import io.javabrains.ratingsdataservice.repository.CustomerRepository;
import io.javabrains.ratingsdataservice.repository.InvoiceRepository;
import io.javabrains.ratingsdataservice.services.CustomerService;
import io.javabrains.ratingsdataservice.services.InvoiceDetailService;
import io.javabrains.ratingsdataservice.services.InvoiceService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class InvoiceTest {

    @Mock
    private InvoiceRepository invoiceRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private InvoiceDetailService invoiceDetailService;

    @InjectMocks
    private InvoiceService invoiceService;

    @Test
    void testAdd1() {
        int invoiceId=1;
        Customer customer = new Customer();
        Invoice invoice = new Invoice(invoiceId, customer, LocalDateTime.now(), LocalDate.now().minusDays(1));
        final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> invoiceService.addInvoice(invoice));
        assertEquals("The invoice is expired", exception.getMessage());
        verifyNoInteractions(invoiceRepository);
    }

    @Test
    void testAdd2() {
//        int id=1; -- no usar el mismo id en customer y invoice
        Customer customer = new Customer(1, "Steph", "Trejos", LocalDate.of(2001, 10, 3), "7207607058", "steph@gmail.com", Customer.Language.ENGLISH);
        Invoice invoice = new Invoice(10, customer, LocalDateTime.now(), LocalDate.now().plusDays(1));
        final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> invoiceService.addInvoice(invoice));
        assertEquals("The customer does not exist", exception.getMessage());
//        customerRepository.save(customer); -- no hay llamados a customerRepository
        verifyNoInteractions(invoiceRepository); // verificar no llamados a invoiceRepository
    }
    @Test
    void testAdd3() {
        int invoiceId=1;
        int customerId=12;
        Customer customer = new Customer(customerId, "Steph", "Trejos", LocalDate.of(2001, 10, 3), "7207607058", "steph@gmail.com", Customer.Language.ENGLISH);
        Invoice invoice = new Invoice(invoiceId, customer, LocalDateTime.now(), LocalDate.now().plusDays(1));
        when(customerRepository.getCustomer(customerId)).thenReturn(customer);

        InvoiceDetail invoiceDetail = new InvoiceDetail();
        List<InvoiceDetail> invoiceDetailsList = new LinkedList<>();
        invoiceDetailsList.add(invoiceDetail);
//        invoiceDetailService.validateInvoiceDetails(invoiceDetail); -- por que??
        invoice.setInvoiceDetails(invoiceDetailsList);
        System.out.println(invoiceDetailsList);

        // when
        invoiceService.addInvoice(invoice);

        verify(customerRepository, times(1)).getCustomer(customerId);
        verifyNoMoreInteractions(customerRepository);

        verify(invoiceDetailService, times(1)).validateInvoiceDetails(invoiceDetail);
        verifyNoMoreInteractions(invoiceDetailService);

        verify(invoiceRepository, times(1)).save(invoice);
        verifyNoMoreInteractions(invoiceRepository);
    }

        @Test
    void testAdd4() { // probar que se llame a invoiceRepository.save, no excepciones
            int invoiceId=1;
            int customerId=12;
            Customer customer = new Customer(customerId, "Steph", "Trejos", LocalDate.of(2001, 10, 3), "7207607058", "steph@gmail.com", Customer.Language.ENGLISH);
            Invoice invoice = new Invoice(invoiceId, customer, LocalDateTime.now(), LocalDate.now().plusDays(1));
            when(customerRepository.getCustomer(customerId)).thenReturn(customer);
            invoiceService.addInvoice(invoice);
            verify(invoiceRepository, times(1)).save(invoice);
            verifyNoMoreInteractions(invoiceRepository);
    }


    @Test
    void testModify1() {
        int invoiceId=1;
        Customer customer = new Customer();
        Invoice invoice = new Invoice(invoiceId, customer, LocalDateTime.now(), LocalDate.now().minusDays(1));
        final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> invoiceService.modifyInvoice(invoice));
        assertEquals("The invoice does not exist", exception.getMessage());
        verify(invoiceRepository, times(1)).getInvoice(invoiceId);
        verifyNoMoreInteractions(invoiceRepository);
    }

    @Test
    void testModify2() {
        int invoiceId=1;
        Customer customer = new Customer();
        Invoice invoice = new Invoice(invoiceId, customer, LocalDateTime.now(), LocalDate.now().minusDays(1));
        when(invoiceRepository.getInvoice(invoiceId)).thenReturn(invoice);
        final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> invoiceService.modifyInvoice(invoice));
        assertEquals("The invoice is expired", exception.getMessage());
        verify(invoiceRepository, times(1)).getInvoice(invoiceId); // verificar llamado a getInvoice
        verifyNoMoreInteractions(invoiceRepository);
    }

    @Test
    void testModify3() {
        int invoiceId=1;
        Customer customer = new Customer(12, "Steph", "Trejos", LocalDate.of(2001, 10, 3), "7207607058", "steph@gmail.com", Customer.Language.ENGLISH);
        Invoice invoice = new Invoice(invoiceId, customer, LocalDateTime.now(), LocalDate.now().plusDays(1));
        when(invoiceRepository.getInvoice(invoiceId)).thenReturn(invoice);
        final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> invoiceService.modifyInvoice(invoice));
        assertEquals("The customer does not exist", exception.getMessage());
//        customerRepository.save(customer); -- no hay llamados a customerRepository
        verify(invoiceRepository, times(1)).getInvoice(invoiceId); // verificar llamado a getInvoice
        verifyNoMoreInteractions(invoiceRepository); // verificar no mas interacciones
    }

    @Test
    void testModify4() {
        int invoiceId=1;
        int customerId=12;
        Customer customer = new Customer(customerId, "Steph", "Trejos", LocalDate.of(2001, 10, 3), "7207607058", "steph@gmail.com", Customer.Language.ENGLISH);
        Invoice invoice = new Invoice(invoiceId, customer, LocalDateTime.now(), LocalDate.now().plusDays(1));
        when(invoiceRepository.getInvoice(invoiceId)).thenReturn(invoice);
        when(customerRepository.getCustomer(customerId)).thenReturn(customer);
        invoiceService.modifyInvoice(invoice);
        verify(invoiceRepository, times(1)).getInvoice(invoiceId); // verificar llamado a getInvoice
        verify(invoiceRepository, times(1)).save(invoice);
        verifyNoMoreInteractions(invoiceRepository);
    }

    @Test
    void testDelete1() {
        int invoiceId=1;
//        Customer customer = new Customer(); -- no esta utilizando variable customer
//        Invoice invoice = new Invoice(id, customer, LocalDateTime.now(), LocalDate.now().plusDays(1)); -- no esta utilizando variable invoice
        final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> invoiceService.deleteInvoice(invoiceId));
        assertEquals("The invoice does not exist", exception.getMessage());
        verify(invoiceRepository, times(1)).getInvoice(invoiceId);
        verifyNoMoreInteractions(invoiceRepository);
    }

    @Test
    void testDelete2() {
        int id=1;
        Customer customer = new Customer();
        Invoice invoice = new Invoice(id, customer, LocalDateTime.now(), LocalDate.now().plusDays(1));
        when(invoiceRepository.getInvoice(id)).thenReturn(invoice);
        invoiceService.deleteInvoice(id);
        verify(invoiceRepository, times(1)).getInvoice(id);
        verify(invoiceRepository, times(1)).delete(id);
        verifyNoMoreInteractions(invoiceRepository);
    }

    @Test
    void testGet1() {
        int id=1;
        Customer customer = new Customer();
        Invoice invoice = new Invoice(id, customer, LocalDateTime.now(), LocalDate.now().plusDays(1));
        when(invoiceRepository.getInvoice(id)).thenReturn(invoice);
        Invoice result = invoiceService.getInvoice(id);
        assertEquals(invoice, result); // verificar el resultado del metodo
        verify(invoiceRepository, times(1)).getInvoice(id);
        verifyNoMoreInteractions(invoiceRepository);
    }
}
