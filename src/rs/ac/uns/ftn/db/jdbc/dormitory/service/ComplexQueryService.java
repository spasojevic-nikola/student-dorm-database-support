package rs.ac.uns.ftn.db.jdbc.dormitory.service;

import java.sql.SQLException;
import java.util.List;

import rs.ac.uns.ftn.db.jdbc.dormitory.dao.ComplexQueryDAO;
import rs.ac.uns.ftn.db.jdbc.dormitory.dao.impl.ComplexQueryDAOImpl;
import rs.ac.uns.ftn.db.jdbc.dormitory.dto.StudentFullDetailsDTO;

public class ComplexQueryService {

    private final ComplexQueryDAO dao = new ComplexQueryDAOImpl();

    public List<StudentFullDetailsDTO> getStudentsWithFullDetails() throws SQLException {
        return dao.fetchStudentsWithDetails();
    }
}
