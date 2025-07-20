package rs.ac.uns.ftn.db.jdbc.dormitory.dto;

import java.math.BigDecimal;

public class StudentPaymentsDTO {
    private String studentName;
    private BigDecimal totalPayments;
    private String facultyName;
    private String dormName;
    private String roomLabel;
    private String cityName;

    public StudentPaymentsDTO() {
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public BigDecimal getTotalPayments() {
        return totalPayments;
    }

    public void setTotalPayments(BigDecimal totalPayments) {
        this.totalPayments = totalPayments;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public String getDormName() {
        return dormName;
    }

    public void setDormName(String dormName) {
        this.dormName = dormName;
    }

    public String getRoomLabel() {
        return roomLabel;
    }

    public void setRoomLabel(String roomLabel) {
        this.roomLabel = roomLabel;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public String toString() {
        return String.format("%-25s %-15s %-20s %-20s %-10s %-20s",
                studentName, 
                totalPayments != null ? totalPayments.toString() : "0",
                facultyName, dormName, roomLabel, cityName);
    }
    
    public static String getFormattedHeader() {
        return String.format("%-25s %-15s %-20s %-20s %-10s %-20s",
                "Student Name",
                "Total Payments",
                "Faculty",
                "Dorm",
                "Room",
                "City");
    }

}
