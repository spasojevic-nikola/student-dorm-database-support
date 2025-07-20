package rs.ac.uns.ftn.db.jdbc.dormitory.model;

public class City {
    private int id;
    private String name;
    private String postalCode;
    private int population;

    public City() {
        super();
    }

    public City(int id, String name, String postalCode, int population) {
        this.id = id;
        this.name = name;
        this.postalCode = postalCode;
        this.population = population;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + population;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((postalCode == null) ? 0 : postalCode.hashCode());
        result = prime * result + id;
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
        City other = (City) obj;
        if (population != other.population)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (postalCode == null) {
            if (other.postalCode != null)
                return false;
        } else if (!postalCode.equals(other.postalCode))
            return false;
        if (id != other.id)
            return false;
        return true;
    }

    // Getters and setters
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

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    @Override
    public String toString() {
        return String.format("%-6d %-30s %-10s %-15d", id, name, postalCode, population);
    }

    public static String getFormattedHeader() {
        return String.format("%-6s %-30s %-10s %-15s", "ID", "NAME", "POSTAL CODE", "POPULATION");
    }
}
