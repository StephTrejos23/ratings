package io.javabrains.ratingsdataservice.models;

public class CuentaAhorro extends Cuenta implements Transacciones {

    // constantes
    private static final float COMISION = 100;
    private static final float INTERES = 0.05f;

    public CuentaAhorro(int id, String numeroCuenta, float saldo) {
        super(id, numeroCuenta, saldo);
    }

    @Override
    public void depositar(float monto) {
        setSaldo(getSaldo() + monto + monto * INTERES);
        System.out.println("Al hacer un deposito, este va a sumar 5% del valor del deposito");
    }

    @Override
    public void retirar(float monto) {
        setSaldo(getSaldo() - monto - COMISION);
        System.out.println("Al hacer un retiro este va a cobrar 100 colones de comision");
    }
}
