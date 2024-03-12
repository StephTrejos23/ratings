package io.javabrains.ratingsdataservice.models;

public abstract class Person {
    private String lastName;

    public Person() {
    }

    public Person(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public abstract void printInfo();
}
