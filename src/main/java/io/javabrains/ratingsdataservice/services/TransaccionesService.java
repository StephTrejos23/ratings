package io.javabrains.ratingsdataservice.services;

import io.javabrains.ratingsdataservice.models.Transacciones;

public class TransaccionesService {

    public void retirar(Transacciones transacciones, float monto) {
        transacciones.retirar(monto);
        transacciones.verSaldo();
    }

    public void depositar(Transacciones transacciones, float monto) {
        transacciones.depositar(monto);
        transacciones.verSaldo();
    }

}
