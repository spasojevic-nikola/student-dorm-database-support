package rs.ac.uns.ftn.db.jdbc.dormitory.dto;

import java.util.List;

import rs.ac.uns.ftn.db.jdbc.dormitory.model.City;
import rs.ac.uns.ftn.db.jdbc.dormitory.model.StudentDorm;

public class CityDormsDTO {
    private City city;
    private List<StudentDorm> dorms;

    public CityDormsDTO(City city, List<StudentDorm> dorms) {
        this.city = city;
        this.dorms = dorms;
    }

    public City getCity() { return city; }
    public List<StudentDorm> getDorms() { return dorms; }
}