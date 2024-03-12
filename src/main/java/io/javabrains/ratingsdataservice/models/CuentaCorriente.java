package io.javabrains.ratingsdataservice.models;

public class CuentaCorriente extends Cuenta implements Transacciones{
    public CuentaCorriente(int id, String numeroCuenta, float saldo) {
        super(id, numeroCuenta, saldo);
    }

    @Override
    public void depositar(float monto) {
        setSaldo(getSaldo()+monto);
        System.out.println("Se esta sumando el monto");
    }

    @Override
    public void retirar(float monto) {
        setSaldo(getSaldo()-monto);
        System.out.println("Se esta restando el monto");
    }
}
