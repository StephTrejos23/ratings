package io.javabrains.ratingsdataservice.model;

import io.javabrains.ratingsdataservice.models.Customer;
import io.javabrains.ratingsdataservice.models.Product;
import io.javabrains.ratingsdataservice.repository.CustomerRepository;
import io.javabrains.ratingsdataservice.services.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class CustomerTest {

    @Mock
    private CustomerRepository customerRepository;
    @InjectMocks
    private CustomerService customerService;

    @Test
    void testAdd1() {
        Customer customer = new Customer(1, "Steph", "Trejos", LocalDate.of(2001, 10, 3), "72076070", "stephgmail.com", Customer.Language.ENGLISH);
//        customer.setEmail(null);
        final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> customerService.addCustomer(customer));
        assertEquals("Email must contain an @.", exception.getMessage());
    }

    @Test
    void testAdd2() {
        Customer customer = new Customer(1, "Steph", "Trejos", LocalDate.of(2001, 10, 3), "72076070", "steph@gmail.com", Customer.Language.ENGLISH);
        final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> customerService.addCustomer(customer));
        assertEquals("Phone must contain 10 digits.", exception.getMessage());
        verifyNoMoreInteractions(customerRepository);
    }

    @ParameterizedTest
    @EmptySource
//    @NullAndEmptySource
    @ValueSource(strings = {"72076070","720760700k"})
    void testAddParameterized(String phone) {
        Customer customer = new Customer(1, "Steph", "Trejos", LocalDate.of(2001, 10, 3), phone, "steph@gmail.com", Customer.Language.ENGLISH);
        final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> customerService.addCustomer(customer));
        assertEquals("Phone must contain 10 digits.", exception.getMessage());
        verifyNoMoreInteractions(customerRepository);
    }

    @Test
    void testAdd3() {
        Customer customer = new Customer(1, "Steph", "Trejos", LocalDate.of(2001, 10, 3), "720760700k", "steph@gmail.com", Customer.Language.ENGLISH);
        final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> customerService.addCustomer(customer));
        assertEquals("Phone must contain 10 digits.", exception.getMessage());
        verifyNoMoreInteractions(customerRepository);
    }

    @Test
    void testAdd4() {
        Customer customer = new Customer(1, "Steph", "Trejos", LocalDate.of(2001, 10, 3), "7207607058", "steph@gmail.com", Customer.Language.ENGLISH);
        customerService.addCustomer(customer);
        verify(customerRepository, times(1)).save(customer);
        verifyNoMoreInteractions(customerRepository);
    }

    @Test
    void testModify1() {
        int id = 1;
        Customer customer = new Customer(1, "Steph", "Trejos", LocalDate.of(2001, 10, 3), "7207607058", "steph@gmail.com", Customer.Language.ENGLISH);

        final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> customerService.modifyCustomer(customer));
        assertEquals("The customer does not exist", exception.getMessage());
        verify(customerRepository, times(1)).getCustomer(id);
        verifyNoMoreInteractions(customerRepository);
    }

    @Test
    void testModify2() {
        int id = 1;
        Customer customer = new Customer(id, "Steph", "Trejos", LocalDate.of(2001, 10, 3), "7207607058", "steph@gmail.com", Customer.Language.ENGLISH);
        when(customerRepository.getCustomer(id)).thenReturn(customer);
        customerService.modifyCustomer(customer);
        verify(customerRepository, times(1)).getCustomer(id);
        verify(customerRepository, times(1)).save(customer);
        verifyNoMoreInteractions(customerRepository);
    }

    @Test
    void testDelete1() {
        int id = 1;

        Customer customer = new Customer(1, "Steph", "Trejos", LocalDate.of(2001, 10, 3), "7207607058", "steph@gmail.com", Customer.Language.ENGLISH);
        when(customerRepository.getCustomer(id)).thenReturn(customer);
        customerService.deleteCustomer(id);
        verify(customerRepository, times(1)).getCustomer(id);
        verify(customerRepository, times(1)).delete(id);
        verifyNoMoreInteractions(customerRepository);
    }

    @Test
    void testDelete2() {
        int id = 1;
        final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> customerService.deleteCustomer(id));
        assertEquals("The customer does not exist", exception.getMessage());
        verify(customerRepository, times(1)).getCustomer(id);
        verifyNoMoreInteractions(customerRepository);
    }

    @Test
    void testGet1() {
        int id = 1;
        Customer customer = new Customer(1, "Steph", "Trejos", LocalDate.of(2001, 10, 3), "7207607058", "steph@gmail.com", Customer.Language.ENGLISH);
        when(customerRepository.getCustomer(id)).thenReturn(customer);
        Customer resultado = customerService.getCustomer(id);

        //assertEquals entre customer y resultado

        verify(customerRepository, times(1)).getCustomer(id);
        verifyNoMoreInteractions(customerRepository);
    }

    @Test
    void testplusOne1() {
        int result = customerService.plusOne(1);
        assertEquals(2, result);
    }

    @Test
    void testplusOne2() {
        assertEquals(3, customerService.plusOne(2));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2})
    void testplusUnion(int number){

        //assertEquals(number + 1, customerService.plusOne(number));
        int result = customerService.plusOne(number);
        assertEquals(number + 1, result);
    }

    @ParameterizedTest
    @CsvSource({"10, 5", "20, 10", "30, 5"})
    void testdivision(int value1, int value2){
        int result = customerService.division(value1, value2);
        assertEquals(value1/value2, result);
    }
}
