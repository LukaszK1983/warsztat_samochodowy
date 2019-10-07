package pl.coderslab.warsztat_samochodowy.model;

import java.sql.Date;

public class Vehicle {
    private int id;
    private int customerId;
    private String brand;
    private String model;
    private int made;
    private String registrationNr;
    private Date nextExam;

    public Vehicle() {
    }

    public Vehicle(String brand, String model, int made, String registrationNr, Date nextExam) {
        this.brand = brand;
        this.model = model;
        this.made = made;
        this.registrationNr = registrationNr;
        this.nextExam = nextExam;
    }

    public Vehicle(int customerId, String brand, String model, int made, String registrationNr, Date nextExam) {
        this.customerId = customerId;
        this.brand = brand;
        this.model = model;
        this.made = made;
        this.registrationNr = registrationNr;
        this.nextExam = nextExam;
    }

    public Vehicle(int id, int customerId, String brand, String model, int made, String registrationNr, Date nextExam) {
        this.id = id;
        this.customerId = customerId;
        this.brand = brand;
        this.model = model;
        this.made = made;
        this.registrationNr = registrationNr;
        this.nextExam = nextExam;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getMade() {
        return made;
    }

    public void setMade(int made) {
        this.made = made;
    }

    public String getRegistrationNr() {
        return registrationNr;
    }

    public void setRegistrationNr(String registrationNr) {
        this.registrationNr = registrationNr;
    }

    public Date getNextExam() {
        return nextExam;
    }

    public void setNextExam(Date nextExam) {
        this.nextExam = nextExam;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", made=" + made +
                ", registrationNr='" + registrationNr + '\'' +
                ", nextExam=" + nextExam +
                '}';
    }
}
