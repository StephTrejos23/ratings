package io.javabrains.ratingsdataservice.services;

import io.javabrains.ratingsdataservice.models.CuentaAhorro;
import io.javabrains.ratingsdataservice.models.CuentaCorriente;
import io.javabrains.ratingsdataservice.models.Transacciones;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class TransaccionesServiceTest {
    @Test
            void test1(){
        TransaccionesService transaccionesService = new TransaccionesService();

        //Transacciones transacciones = new CuentaAhorro(1,"23224544",48900);
        Transacciones transacciones = new CuentaCorriente(1,"23224544",48900);
        transaccionesService.depositar(transacciones,1000 );
        //assertEquals(25400, );

        List<String> stringList1 = new ArrayList<>();
        List<String> stringList2 = new LinkedList<>();

        //Set
        //Map

        //Collections
    }

}
