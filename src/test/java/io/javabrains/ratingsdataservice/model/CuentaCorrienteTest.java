package io.javabrains.ratingsdataservice.model;

import io.javabrains.ratingsdataservice.models.Cuenta;
import io.javabrains.ratingsdataservice.models.CuentaAhorro;
import io.javabrains.ratingsdataservice.models.CuentaCorriente;
import io.javabrains.ratingsdataservice.models.Transacciones;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class CuentaCorrienteTest {

    @Test
    void test1() {
       CuentaCorriente cuentaCorriente = new CuentaCorriente(2,"2827474839", 350000);
        assertEquals(2, cuentaCorriente.getId());
        assertEquals("2827474839", cuentaCorriente.getNumeroCuenta());
        assertEquals(350000, cuentaCorriente.getSaldo());

        cuentaCorriente.depositar(10000);
//        cuentaCorriente.setId(3);
//        cuentaCorriente.setNumeroCuenta("2827475677");
//        cuentaCorriente.setSaldo(360000);
//        assertEquals(3, cuentaCorriente.getId());
//        assertEquals("2827475677", cuentaCorriente.getNumeroCuenta());
        assertEquals(360000, cuentaCorriente.getSaldo());


        cuentaCorriente.retirar(60000);
//        cuentaCorriente.setId(4);
//        cuentaCorriente.setNumeroCuenta("2827475677");
//        cuentaCorriente.setSaldo(300000);
//        assertEquals(4, cuentaCorriente.getId());
//        assertEquals("2827475677", cuentaCorriente.getNumeroCuenta());
        assertEquals(300000, cuentaCorriente.getSaldo());


    }
}
