package rs.ac.uns.ftn.db.jdbc.dormitory.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import rs.ac.uns.ftn.db.jdbc.dormitory.dao.StudentDormDAO;
import rs.ac.uns.ftn.db.jdbc.dormitory.dao.impl.StudentDormDAOImpl;
import rs.ac.uns.ftn.db.jdbc.dormitory.model.StudentDorm;

public class StudentDormService {

    private static final StudentDormDAO dormDAO = new StudentDormDAOImpl();

    public ArrayList<StudentDorm> getAll() throws SQLException {
        return (ArrayList<StudentDorm>) dormDAO.findAll();
    }

    public StudentDorm getById(int id) throws SQLException {
        return dormDAO.findById(id);
    }

    public boolean existsById(int id) throws SQLException {
        return dormDAO.existsById(id);
    }

    public boolean save(StudentDorm dorm) throws SQLException {
        return dormDAO.save(dorm);
    }

    public boolean deleteById(int id) throws SQLException {
        return dormDAO.deleteById(id);
    }

    public int saveAll(List<StudentDorm> dormList) throws SQLException {
        return dormDAO.saveAll(dormList);
    }
}
