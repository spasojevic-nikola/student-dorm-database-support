package rs.ac.uns.ftn.db.jdbc.dormitory.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import rs.ac.uns.ftn.db.jdbc.dormitory.dao.CityDAO;
import rs.ac.uns.ftn.db.jdbc.dormitory.dao.StudentDormDAO;
import rs.ac.uns.ftn.db.jdbc.dormitory.dao.impl.CityDAOImpl;
import rs.ac.uns.ftn.db.jdbc.dormitory.dao.impl.StudentDormDAOImpl;
import rs.ac.uns.ftn.db.jdbc.dormitory.dto.CityDormsDTO;
import rs.ac.uns.ftn.db.jdbc.dormitory.model.City;
import rs.ac.uns.ftn.db.jdbc.dormitory.model.StudentDorm;

public class StudentDormService {

    private static final StudentDormDAO studentDormDAO = new StudentDormDAOImpl();
    private static final CityDAO cityDAO = new CityDAOImpl();

    public ArrayList<StudentDorm> getAll() throws SQLException {
        return (ArrayList<StudentDorm>) studentDormDAO.findAll();
    }

    public StudentDorm getById(int id) throws SQLException {
        return studentDormDAO.findById(id);
    }

    public boolean existsById(int id) throws SQLException {
        return studentDormDAO.existsById(id);
    }

    public boolean save(StudentDorm dorm) throws SQLException {
        return studentDormDAO.save(dorm);
    }

    public boolean deleteById(int id) throws SQLException {
        return studentDormDAO.deleteById(id);
    }

    public int saveAll(List<StudentDorm> dormList) throws SQLException {
        return studentDormDAO.saveAll(dormList);
    }

    public List<CityDormsDTO> getDormsGroupedByCity() throws SQLException {
        List<CityDormsDTO> result = new ArrayList<>();

        //method in the CRUDDao interface is defined to return an Iterable<City>, not a List<City>.
        Iterable<City> citiesIterable = cityDAO.findAll();
        List<City> cities = new ArrayList<>();
        citiesIterable.forEach(cities::add);

        for (City city : cities) {
            List<StudentDorm> dorms = studentDormDAO.findByCityId(city.getId()); // Moraš implementirati ovaj metod
            result.add(new CityDormsDTO(city, dorms));
        }
        return result;
    }
}
