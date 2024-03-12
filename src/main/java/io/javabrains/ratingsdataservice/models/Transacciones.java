package io.javabrains.ratingsdataservice.models;

public interface Transacciones {

    void depositar(float monto);

    void retirar(float monto);

     void verSaldo() ;
}

