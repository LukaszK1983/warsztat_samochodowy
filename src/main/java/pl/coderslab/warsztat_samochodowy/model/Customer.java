package pl.coderslab.warsztat_samochodowy.model;

import java.sql.Date;

public class Customer {
    private int id;
    private String firstName;
    private String surname;
    private Date birthYear;

    public Customer() {
    }

    public Customer(String surname) {
        this.surname = surname;
    }

    public Customer(String firstName, String surname, Date birthYear) {
        this.firstName = firstName;
        this.surname = surname;
        this.birthYear = birthYear;
    }

    public Customer(int id, String firstName, String surname, Date birthYear) {
        this.id = id;
        this.firstName = firstName;
        this.surname = surname;
        this.birthYear = birthYear;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return this.surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Date birthYear) {
        this.birthYear = birthYear;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstname='" + firstName + '\'' +
                ", surname='" + surname + '\'' +
                ", birthYear=" + birthYear +
                '}';
    }
}
