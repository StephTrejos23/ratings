package io.javabrains.ratingsdataservice.models;

public abstract class Cuenta {
    private int id;
    private String numeroCuenta;
    private float saldo;

    public Cuenta() {
    }

    public Cuenta(int id, String numeroCuenta, float saldo) {
        this.id = id;
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public void verSaldo() {
        System.out.println("El saldo es " + saldo + " colones");

    }
}
