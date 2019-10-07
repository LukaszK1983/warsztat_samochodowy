package pl.coderslab.warsztat_samochodowy.model;

import java.sql.Timestamp;

public class Order {
    private int id;
    private Timestamp receiptDate;
    private Timestamp plannedRepairDate;
    private Timestamp startRepairDate;
    private int employeeId;
    private String problemDesc;
    private String repairDesc;
    private String status;
    private int vehicleId;
    private double repairCost;
    private double partsCost;
    private double manHourCost;
    private int numberManHours;
    private String firstName;
    private String surname;

    public Order() {
    }

    public Order(Timestamp receiptDate, String problemDesc, String status) {
        this.receiptDate = receiptDate;
        this.problemDesc = problemDesc;
        this.status = status;
    }

    public Order(String firstName, String surname, int numberManHours) {
        this.firstName = firstName;
        this.surname = surname;
        this.numberManHours = numberManHours;
    }

    public Order(Timestamp receiptDate, Timestamp plannedRepairDate, int employeeId, String problemDesc, int vehicleId) {
        this.receiptDate = receiptDate;
        this.plannedRepairDate = plannedRepairDate;
        this.employeeId = employeeId;
        this.problemDesc = problemDesc;
        this.vehicleId = vehicleId;
    }

    public Order(Timestamp receiptDate, Timestamp plannedRepairDate, Timestamp startRepairDate, int employeeId, String problemDesc, String repairDesc, String status, int vehicleId, double repairCost, double partsCost, double manHourCost, int numberManHours) {
        this.receiptDate = receiptDate;
        this.plannedRepairDate = plannedRepairDate;
        this.startRepairDate = startRepairDate;
        this.employeeId = employeeId;
        this.problemDesc = problemDesc;
        this.repairDesc = repairDesc;
        this.status = status;
        this.vehicleId = vehicleId;
        this.repairCost = repairCost;
        this.partsCost = partsCost;
        this.manHourCost = manHourCost;
        this.numberManHours = numberManHours;
    }

    public Order(int id, Timestamp receiptDate, Timestamp plannedRepairDate, Timestamp startRepairDate, int employeeId, String problemDesc, String repairDesc, String status, int vehicleId, double repairCost, double partsCost, double manHourCost, int numberManHours) {
        this.id = id;
        this.receiptDate = receiptDate;
        this.plannedRepairDate = plannedRepairDate;
        this.startRepairDate = startRepairDate;
        this.employeeId = employeeId;
        this.problemDesc = problemDesc;
        this.repairDesc = repairDesc;
        this.status = status;
        this.vehicleId = vehicleId;
        this.repairCost = repairCost;
        this.partsCost = partsCost;
        this.manHourCost = manHourCost;
        this.numberManHours = numberManHours;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(Timestamp receiptDate) {
        this.receiptDate = receiptDate;
    }

    public Timestamp getPlannedRepairDate() {
        return plannedRepairDate;
    }

    public void setPlannedRepairDate(Timestamp plannedRepairDate) {
        this.plannedRepairDate = plannedRepairDate;
    }

    public Timestamp getStartRepairDate() {
        return startRepairDate;
    }

    public void setStartRepairDate(Timestamp startRepairDate) {
        this.startRepairDate = startRepairDate;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getProblemDesc() {
        return problemDesc;
    }

    public void setProblemDesc(String problemDesc) {
        this.problemDesc = problemDesc;
    }

    public String getRepairDesc() {
        return repairDesc;
    }

    public void setRepairDesc(String repairDesc) {
        this.repairDesc = repairDesc;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public double getRepairCost() {
        return repairCost;
    }

    public void setRepairCost(double repairCost) {
        this.repairCost = repairCost;
    }

    public double getPartsCost() {
        return partsCost;
    }

    public void setPartsCost(double partsCost) {
        this.partsCost = partsCost;
    }

    public double getManHourCost() {
        return manHourCost;
    }

    public void setManHourCost(double manHourCost) {
        this.manHourCost = manHourCost;
    }

    public int getNumberManHours() {
        return numberManHours;
    }

    public void setNumberManHours(int numberManHours) {
        this.numberManHours = numberManHours;
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

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", receiptDate=" + receiptDate +
                ", plannedRepairDate=" + plannedRepairDate +
                ", startRepairDate=" + startRepairDate +
                ", employeeId=" + employeeId +
                ", problemDesc='" + problemDesc + '\'' +
                ", repairDesc='" + repairDesc + '\'' +
                ", status=" + status +
                ", vehicleId=" + vehicleId +
                ", repairCost=" + repairCost +
                ", partsCost=" + partsCost +
                ", manHourCost=" + manHourCost +
                ", numberManHours=" + numberManHours +
                '}';
    }
}
