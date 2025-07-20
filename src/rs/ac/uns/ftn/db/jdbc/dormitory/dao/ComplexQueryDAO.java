package rs.ac.uns.ftn.db.jdbc.dormitory.dao;

import java.sql.SQLException;
import java.util.List;

import rs.ac.uns.ftn.db.jdbc.dormitory.dto.StudentFullDetailsDTO;
import rs.ac.uns.ftn.db.jdbc.dormitory.dto.StudentPaymentsDTO;

public interface ComplexQueryDAO {

    List<StudentFullDetailsDTO> fetchStudentsWithDetails() throws SQLException;
    List<StudentPaymentsDTO> fetchPaymentsWithDetails() throws SQLException;
}
