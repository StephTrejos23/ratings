package io.javabrains.ratingsdataservice.services;

import io.javabrains.ratingsdataservice.models.*;
import io.javabrains.ratingsdataservice.repository.CustomerRepository;
import io.javabrains.ratingsdataservice.repository.InvoiceRepository;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class InvoiceServiceTest {

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
        int invoiceId = 1;
        Customer customer = new Customer();
        Invoice invoice = new Invoice(invoiceId, customer, LocalDateTime.now(), LocalDate.now().minusDays(1));
        final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> invoiceService.addInvoice(invoice));
        assertEquals("The invoice is expired", exception.getMessage());
        verifyNoInteractions(invoiceRepository);
    }

    @Test
    void testAdd2() {
        int customerId=1; //-- no usar el mismo id en customer y invoice
        Customer customer = new Customer(1, "Steph", "Trejos", LocalDate.of(2001, 10, 3), "7207607058", "steph@gmail.com", Language.ENGLISH);
        Invoice invoice = new Invoice(10, customer, LocalDateTime.now(), LocalDate.now().plusDays(1));

        final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> invoiceService.addInvoice(invoice));
        assertEquals("The customer does not exist", exception.getMessage());

//        customerRepository.save(customer); -- no hay llamados a customerRepository
        verify(customerRepository, times(1)).findById(customerId);
        verifyNoMoreInteractions(customerRepository);


    }

    @Test
    void testAdd3() {
        int invoiceId = 1;
        int customerId = 12;
        Customer customer = new Customer(customerId, "Steph", "Trejos", LocalDate.of(2001, 10, 3), "7207607058", "steph@gmail.com", Language.ENGLISH);
        Invoice invoice = new Invoice(invoiceId, customer, LocalDateTime.now(), LocalDate.now().plusDays(1));
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));

        InvoiceDetail invoiceDetail = new InvoiceDetail();
//        List<InvoiceDetail> invoiceDetailsList = new LinkedList<>();
//        invoiceDetailsList.add(invoiceDetail);
//        invoiceDetailService.validateInvoiceDetails(invoiceDetail); -- por que??
//        invoice.setInvoiceDetails(invoiceDetailsList);
//        System.out.println(invoiceDetailsList);
        invoice.setInvoiceDetails(List.of(invoiceDetail));

        // when
        invoiceService.addInvoice(invoice);

        verify(customerRepository, times(1)).findById(customerId);
        verifyNoMoreInteractions(customerRepository);

        verify(invoiceDetailService, times(1)).validateInvoiceDetails(invoiceDetail);
        verifyNoMoreInteractions(invoiceDetailService);

        verify(invoiceRepository, times(1)).save(invoice);
        verifyNoMoreInteractions(invoiceRepository);
    }


    @Test
    void testModify1() {
        int invoiceId = 1;
        Customer customer = new Customer();
        Invoice invoice = new Invoice(invoiceId, customer, LocalDateTime.now(), LocalDate.now().minusDays(1));
        final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> invoiceService.modifyInvoice(invoice));
        assertEquals("The invoice does not exist", exception.getMessage());
        verify(invoiceRepository, times(1)).findById(invoiceId);
        verifyNoMoreInteractions(invoiceRepository);
    }

    @Test
    void whenInvoiceIsExpired_thenExceptionIsThrown() {
        int invoiceId = 1;
        Customer customer = new Customer();
        Invoice invoice = new Invoice(invoiceId, customer, LocalDateTime.now(), LocalDate.now().minusDays(1));
        when(invoiceRepository.findById(invoiceId)).thenReturn(Optional.of(invoice));
        final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> invoiceService.modifyInvoice(invoice));
        assertEquals("The invoice is expired", exception.getMessage());
        verify(invoiceRepository, times(1)).findById(invoiceId); // verificar llamado a getInvoice
        verifyNoMoreInteractions(invoiceRepository);
    }

    @Test
    void testModify3() {
        int invoiceId = 1;
        Customer customer = new Customer(12, "Steph", "Trejos", LocalDate.of(2001, 10, 3), "7207607058", "steph@gmail.com", Language.ENGLISH);
        Invoice invoice = new Invoice(invoiceId, customer, LocalDateTime.now(), LocalDate.now().plusDays(1));
        when(invoiceRepository.findById(invoiceId)).thenReturn(Optional.of(invoice));
        final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> invoiceService.modifyInvoice(invoice));
        assertEquals("The customer does not exist", exception.getMessage());
//        customerRepository.save(customer); -- no hay llamados a customerRepository
        verify(invoiceRepository, times(1)).findById(invoiceId); // verificar llamado a getInvoice
        verifyNoMoreInteractions(invoiceRepository); // verificar no mas interacciones
    }

    @Test
    void testModify4() {
        int invoiceId = 1;
        int customerId = 12;
        Customer customer = new Customer(customerId, "Steph", "Trejos", LocalDate.of(2001, 10, 3), "7207607058", "steph@gmail.com", Language.ENGLISH);
        Invoice invoice = new Invoice(invoiceId, customer, LocalDateTime.now(), LocalDate.now().plusDays(1));
        when(invoiceRepository.findById(invoiceId)).thenReturn(Optional.of(invoice));
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
        invoice.setInvoiceDetails(List.of());
        invoiceService.modifyInvoice(invoice);
        verify(invoiceRepository, times(1)).findById(invoiceId); // verificar llamado a getInvoice
        verify(invoiceRepository, times(1)).save(invoice);
        verifyNoMoreInteractions(invoiceRepository);
    }

    @Test
    void testDelete1() {
        int invoiceId = 1;
//        Customer customer = new Customer(); -- no esta utilizando variable customer
//        Invoice invoice = new Invoice(id, customer, LocalDateTime.now(), LocalDate.now().plusDays(1)); -- no esta utilizando variable invoice
        final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> invoiceService.deleteInvoice(invoiceId));
        assertEquals("The invoice does not exist", exception.getMessage());
        verify(invoiceRepository, times(1)).findById(invoiceId);
        verifyNoMoreInteractions(invoiceRepository);
    }

    @Test
    void testDelete2() {
        int id = 1;
        Customer customer = new Customer();
        Invoice invoice = new Invoice(id, customer, LocalDateTime.now(), LocalDate.now().plusDays(1));
        when(invoiceRepository.findById(id)).thenReturn(Optional.of(invoice));
        invoiceService.deleteInvoice(id);
        verify(invoiceRepository, times(1)).findById(id);
        verify(invoiceRepository, times(1)).deleteById(id);
        verifyNoMoreInteractions(invoiceRepository);
    }

    @Test
    void testGet1() {
        int id = 1;
        Customer customer = new Customer();
        Invoice invoice = new Invoice(id, customer, LocalDateTime.now(), LocalDate.now().plusDays(1));
        when(invoiceRepository.findById(id)).thenReturn(Optional.of(invoice));
        Optional<Invoice> result = invoiceService.getInvoice(id);
        assertEquals(Optional.of(invoice), result); // verificar el resultado del metodo
        verify(invoiceRepository, times(1)).findById(id);
        verifyNoMoreInteractions(invoiceRepository);
    }
}
