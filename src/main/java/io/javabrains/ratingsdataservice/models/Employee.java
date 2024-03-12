package io.javabrains.ratingsdataservice.models;

public class Employee extends Person implements EmployeeTransactions{
    private double salary;
    private double ccss;

    public Employee() {
    }

    public Employee(String lastName, double salary, double ccss) {
        super(lastName);
        this.salary = salary;
        this.ccss = ccss;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getCcss() {
        return ccss;
    }

    public void setCcss(double ccss) {
        this.ccss = ccss;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "salary=" + salary +
                ", ccss=" + ccss +
                '}';
    }

    @Override
    public void printInfo() {
        System.out.println(this.toString());
    }

    @Override
    public void paySalary(double salary) {
        System.out.println("Se esta pagando el salario de " + salary + " colones");
        this.salary = salary;
        this.ccss = EmployeeTransactions.tenPercent(salary);
    }

}
