package io.javabrains.ratingsdataservice.models;

import java.time.LocalDate;

public class Customer {

    public enum Language{
        ENGLISH, SPANISH
        //ID, BIRTHDATE, FIRSTNAME, LASTNAME, PHONE, EMAIL, LANGUAGE
    };
    private int id;
    private String firstName;
    private String lastName;

    private LocalDate birthDate;
    private String phone;
    private String email;
    private Language language;

    public Customer() {
    }

    public Customer(int id, String firstName, String lastName, LocalDate birthDate, String phone, String email, Language language) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.phone = phone;
        this.email = email;
        this.language = language;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }
}
