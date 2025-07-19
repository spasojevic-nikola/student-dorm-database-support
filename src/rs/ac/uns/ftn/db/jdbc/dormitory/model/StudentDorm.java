package rs.ac.uns.ftn.db.jdbc.dormitory.model;

public class StudentDorm {
    private int id;
    private String name;
    private String address;
    private int capacity;
    private int cityId; // FK to City

    public StudentDorm() {
        super();
    }

    public StudentDorm(int id, String name, String address, int capacity, int cityId) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.capacity = capacity;
        this.cityId = cityId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + cityId;
        result = prime * result + id;
        result = prime * result + capacity;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((address == null) ? 0 : address.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        StudentDorm other = (StudentDorm) obj;
        if (cityId != other.cityId)
            return false;
        if (id != other.id)
            return false;
        if (capacity != other.capacity)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (address == null) {
            if (other.address != null)
                return false;
        } else if (!address.equals(other.address))
            return false;
        return true;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    @Override
    public String toString() {
        return String.format("%-6d %-30s %-40s %-10d %-6d", id, name, address, capacity, cityId);
    }

    public static String getFormattedHeader() {
        return String.format("%-6s %-30s %-40s %-10s %-6s", "ID", "NAME", "ADDRESS", "CAPACITY", "CITY_ID");
    }
}
