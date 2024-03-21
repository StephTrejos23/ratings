package io.javabrains.ratingsdataservice.services;

import io.javabrains.ratingsdataservice.models.Product;
import io.javabrains.ratingsdataservice.models.Supplier;
import io.javabrains.ratingsdataservice.repository.ProductRepository;
import io.javabrains.ratingsdataservice.repository.SupplierRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;
    private SupplierRepository supplierRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    void test1() {
        Product product = new Product(1, "Lizano", LocalDate.of(2024, 3, 6), 4500);

        final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> productService.add(product));
        assertEquals("id must be null", exception.getMessage());

        verifyNoInteractions(productRepository);
    }


    @Test
    void test2() {
        Product product = new Product(null, "Lizano", LocalDate.now().minusDays(1), 4500);

        final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> productService.add(product));
        assertEquals("The product is expired", exception.getMessage());

        verifyNoInteractions(productRepository);
    }

    @Test
    void test3() {
        Product product = new Product(null, "Lizano", LocalDate.now().plusDays(1), 0.0f);

        final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> productService.add(product));
        assertEquals("The price of the product cannot be 0!", exception.getMessage());

        verifyNoInteractions(productRepository);

    }

    @Test
    void test4() {
        Product product = new Product(null, "Lizano", LocalDate.now().plusDays(1), 4500);

        final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> productService.add(product));
        assertEquals("Supplier does not exist", exception.getMessage());

        verifyNoInteractions(productRepository);
    }

    @Test
    void test5() {
        int supplierId= 1;
        Supplier supplier = new Supplier(supplierId, "Ejm", "Poas","7207607090");
        Product product = new Product(null, "Lizano", LocalDate.now().plusDays(1), 4500, supplier);
        productService.add(product);
        verify(productRepository, times(1)).save(product);
        verifyNoMoreInteractions(productRepository);
    }

    @Test
    void testModify1() {
        Product product = new Product(null, "Pasta", LocalDate.of(2024, 3, 6), 4500);

        final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> productService.modify(product));
        assertEquals("id cannot be null", exception.getMessage());

        verifyNoInteractions(productRepository);
    }

    @Test
    void testModify2() {
        Product product = new Product(1, "Pasta", LocalDate.now().minusDays(1), 0.0f);

        final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> productService.modify(product));
        assertEquals("The price of the product cannot be 0!", exception.getMessage());

        verifyNoInteractions(productRepository);
    }
    @Test
    void testModify3() {
        Product product = new Product(1, "Lizano", LocalDate.now().plusDays(1), 4500);
        final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> productService.modify(product));
        assertEquals("Supplier does not exist", exception.getMessage());
        verifyNoMoreInteractions(productRepository);
    }
    @Test
    void testModify4() {
        Product product = new Product(1, "Pasta", LocalDate.now().plusDays(1), 4500, new Supplier());
        productService.modify(product);
        verify(productRepository, times(1)).save(product);
        verifyNoMoreInteractions(productRepository);


    }

    @Test
    void testDelete1() {
        Integer id = 1;
        final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> productService.delete(id));
        assertEquals("The product does not exist", exception.getMessage());
        verify(productRepository, times(1)).findById(id);
        verifyNoMoreInteractions(productRepository);
    }

    @Test
    void testDelete2() {
        Integer id = 2;

        Product product = new Product(id);
        when(productRepository.findById(id)).thenReturn(Optional.of(product));
        productService.delete(id);
        verify(productRepository, times(1)).findById(id);
        verify(productRepository, times(1)).deleteById(id);
        verifyNoMoreInteractions(productRepository);
    }

    @Test
    void testGet1() {
        int id = 1;
        Product product = new Product(id);
        when(productRepository.findById(id)).thenReturn(Optional.of(product));
        Optional<Product> result = productService.getProduct(id);
        assertEquals(Optional.of(product), result);
        verify(productRepository, times(1)).findById(id);
        verifyNoMoreInteractions(productRepository);
    }
}

