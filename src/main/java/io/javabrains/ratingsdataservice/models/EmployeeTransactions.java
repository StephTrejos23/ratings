package io.javabrains.ratingsdataservice.models;

public interface EmployeeTransactions {
//    double amount = 0; // no pueden ir variables
    void paySalary(double salary);

    static double tenPercent(double salary){
        return salary * 0.1;
    }
}
      


