package pl.coderslab.warsztat_samochodowy.model;

public class Employee {
    private int id;
    private String firstName;
    private String surname;
    private String email;
    private int phone;
    private String note;
    private double manHour;

    public Employee() {
    }

    public Employee(String firstName, String surname, String email, int phone, String note, double manHour) {
        this.firstName = firstName;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        this.note = note;
        this.manHour = manHour;
    }

    public Employee(int id, String firstName, String surname, String email, int phone, String note, double manHour) {
        this.id = id;
        this.firstName = firstName;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        this.note = note;
        this.manHour = manHour;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public double getManHour() {
        return manHour;
    }

    public void setManHour(double manHour) {
        this.manHour = manHour;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", phone=" + phone +
                ", note='" + note + '\'' +
                ", manHour=" + manHour +
                '}';
    }
}
