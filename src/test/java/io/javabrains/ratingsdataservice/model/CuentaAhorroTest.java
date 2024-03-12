package io.javabrains.ratingsdataservice.model;

import io.javabrains.ratingsdataservice.models.CuentaAhorro;
import io.javabrains.ratingsdataservice.models.CuentaCorriente;
import io.javabrains.ratingsdataservice.models.Transacciones;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class CuentaAhorroTest {
    @Test
    void test1() {
        CuentaAhorro cuentaAhorro = new CuentaAhorro(2,"2827474839", 25000);
        assertEquals(2, cuentaAhorro.getId());
        assertEquals("2827474839", cuentaAhorro.getNumeroCuenta());
        assertEquals(25000, cuentaAhorro.getSaldo());

        cuentaAhorro.depositar(10000);
        assertEquals(35500, cuentaAhorro.getSaldo());


        cuentaAhorro.retirar(10000);
        assertEquals(25400, cuentaAhorro.getSaldo());



    }
}
