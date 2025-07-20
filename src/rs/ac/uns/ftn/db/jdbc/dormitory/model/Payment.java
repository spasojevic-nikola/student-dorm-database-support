package rs.ac.uns.ftn.db.jdbc.dormitory.model;

import java.sql.Date;

public class Payment {

    private int id;
    private double totalAmount;
    private String paymentType;
    private Date paymentDate;
    private String description;
    private int studentId;

    public Payment() {
    }

    public Payment(int id, double totalAmount, String paymentType, Date paymentDate, String description, int studentId) {
        this.id = id;
        this.totalAmount = totalAmount;
        this.paymentType = paymentType;
        this.paymentDate = paymentDate;
        this.description = description;
        this.studentId = studentId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
}
