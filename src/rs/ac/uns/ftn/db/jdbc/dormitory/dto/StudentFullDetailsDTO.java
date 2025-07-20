package rs.ac.uns.ftn.db.jdbc.dormitory.dto;

public class StudentFullDetailsDTO {

    private String studentName;
    private String facultyName;
    private String dormName;
    private String roomLabel;
    private String cityName;
    private String inventorySummary;

    @Override
    public String toString() {
        return String.format("%-20s %-20s %-20s %-10s %-15s %-30s",
            studentName, facultyName, dormName, roomLabel, cityName, inventorySummary);
    }

    public static String getFormattedHeader() {
        return String.format("%-20s %-20s %-20s %-10s %-15s %-30s",
            "Student", "Faculty", "Dormitory", "Room", "City", "Inventory");
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
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

    public String getInventorySummary() {
        return inventorySummary;
    }

    public void setInventorySummary(String inventorySummary) {
        this.inventorySummary = inventorySummary;
    }
    
}
