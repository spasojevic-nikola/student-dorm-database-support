package rs.ac.uns.ftn.db.jdbc.dormitory.dao;

import java.sql.Connection;
import java.sql.SQLException;

import rs.ac.uns.ftn.db.jdbc.dormitory.model.City;
import rs.ac.uns.ftn.db.jdbc.dormitory.model.Student;

public interface StudentDAO extends CRUDDao<Student, Integer> {
    void saveTransactional(Connection conn, Student student) throws SQLException;

    void updateTransactional(Connection conn, Student student) throws SQLException;

    boolean deleteByIdTransactional(Connection conn, int studentId) throws SQLException;
    
    boolean existsById(Connection conn, int studentId) throws SQLException;

    Student findById(Connection conn, int studentId) throws SQLException;
}