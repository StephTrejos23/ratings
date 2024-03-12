package io.javabrains.ratingsdataservice.model;

import io.javabrains.ratingsdataservice.models.Customer;
import io.javabrains.ratingsdataservice.models.Product;
import io.javabrains.ratingsdataservice.models.Supplier;
import io.javabrains.ratingsdataservice.repository.SupplierRepository;
import io.javabrains.ratingsdataservice.services.SupplierService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SupplierTest {
    @Mock
    private SupplierRepository supplierRepository;


    @InjectMocks
    private SupplierService supplierService;

    @ParameterizedTest
    @ValueSource(strings = {"72076070","720760700k"})
    @EmptySource
    void addSupplier1(String phone) {
        int supplierId=1;
        Supplier supplier = new Supplier(supplierId, "Steph", "San Rafael", phone);
        final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> supplierService.addSupplier(supplier));
        assertEquals("Phone must contain 10 digits.", exception.getMessage());
        verifyNoInteractions(supplierRepository);


    }

    @ParameterizedTest
    @NullAndEmptySource
    void addSupplier2(String name) {
        int supplierId=1;
        Supplier supplier = new Supplier(supplierId, name, "San Rafael", "7020503323");
        final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> supplierService.addSupplier(supplier));
        assertEquals("Name cannot be null or blank", exception.getMessage());
        verifyNoInteractions(supplierRepository);
    }

    @Test
    void addSupplier3() {
        int supplierId=1;
        Supplier supplier = new Supplier(supplierId, "Stephanie", "San Rafael", "7020503323");
        supplierService.addSupplier(supplier);
        verify(supplierRepository, times(1)).save(supplier);
        verifyNoMoreInteractions(supplierRepository);
    }

    @ParameterizedTest
    @ValueSource(strings = {"72076070","720760700k"})
    @EmptySource
    void updateSupplier1(String phone) {
        int supplierId = 1;
        Supplier supplier = new Supplier(supplierId, "Steph", "San Rafael", phone);
        final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> supplierService.updateSupplier(supplier));
        assertEquals("Phone must contain 10 digits.", exception.getMessage());
        verifyNoInteractions(supplierRepository);
    }

    @ParameterizedTest
    @NullAndEmptySource
    void updateSupplier2(String name) {
        int supplierId=1;
        Supplier supplier = new Supplier(supplierId, name, "San Rafael", "7020503323");
        final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> supplierService.updateSupplier(supplier));
        assertEquals("Name cannot be null or blank", exception.getMessage());
        verifyNoInteractions(supplierRepository);
    }

    @Test
    void updateSupplier3() {
        int supplierId=1;
        Supplier supplier = new Supplier(supplierId, "Stephanie", "San Rafael", "7020503323");
        supplierService.updateSupplier(supplier);
        verify(supplierRepository, times(1)).save(supplier);
        verifyNoMoreInteractions(supplierRepository);
    }

    @Test
    void deleteSupplier1() {
        int supplierId=1;
        final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> supplierService.deleteSupplier(supplierId));
        assertEquals("The supply does not exist", exception.getMessage());
        verify(supplierRepository, times(1)).getSupplier(supplierId);
        verifyNoMoreInteractions(supplierRepository);
    }

    @Test
    void deleteSupplier2() {
        int supplierId=1;
        Supplier supplier= new Supplier();
        when(supplierRepository.getSupplier(supplierId)).thenReturn(supplier);
        supplierService.deleteSupplier(supplierId);
        verify(supplierRepository, times(1)).getSupplier(supplierId);
        verify(supplierRepository, times(1)).delete(supplierId);
        verifyNoMoreInteractions(supplierRepository);
    }

    @Test
    void getSupplier1() {
        int supplierId = 1;
        Supplier supplier= new Supplier();
        when(supplierRepository.getSupplier(supplierId)).thenReturn(supplier);
        Supplier result = supplierService.getSupplier(supplierId);
        assertEquals(supplier, result);
        verify(supplierRepository, times(1)).getSupplier(supplierId);
        verifyNoMoreInteractions(supplierRepository);
    }

    @Test
    void existSupplier1(){
        int supplierId=1;
        Supplier supplier= new Supplier();
        when(supplierRepository.getSupplier(supplierId)).thenReturn(supplier);
        boolean existSupplier = supplierService.existSupplier(supplierId);
        assertTrue(existSupplier);
        verify(supplierRepository, times(1)).getSupplier(supplierId);
        verifyNoMoreInteractions(supplierRepository);

    }

    @Test
    void existSupplier2(){
        int supplierId=1;
        boolean existSupplier = supplierService.existSupplier(supplierId);
        assertFalse(existSupplier);
        verify(supplierRepository, times(1)).getSupplier(supplierId);
        verifyNoMoreInteractions(supplierRepository);

    }
}
