package rs.ac.uns.ftn.db.jdbc.dormitory.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import rs.ac.uns.ftn.db.jdbc.dormitory.connection.ConnectionUtil_HikariCP;
import rs.ac.uns.ftn.db.jdbc.dormitory.dao.ComplexQueryDAO;
import rs.ac.uns.ftn.db.jdbc.dormitory.dto.StudentFullDetailsDTO;
import rs.ac.uns.ftn.db.jdbc.dormitory.dto.StudentPaymentsDTO;

public class ComplexQueryDAOImpl implements ComplexQueryDAO {

    @Override
    public List<StudentFullDetailsDTO> fetchStudentsWithDetails() throws SQLException {
        List<StudentFullDetailsDTO> result = new ArrayList<>();

        String query =
        	    "SELECT s.ID_STUD AS student_id, " +
        	    "       s.IME_STUD || ' ' || s.PRZ_STUD AS student_name, " +
        	    "       f.NAZ_FAK AS faculty_name, " +
        	    "       d.NAZ_STD AS dorm_name, " +
        	    "       r.ID_SOB AS room_id, " +
        	    "       g.NAZ_GR AS city_name, " +
        	    "       (SELECT LISTAGG(i.NAZ_INV || ' (' || i.KOLICINA || ')', ', ') " +
        	    "          WITHIN GROUP (ORDER BY i.NAZ_INV) " +
        	    "        FROM INVENTAR i WHERE i.ID_SOB = r.ID_SOB) AS inventory_summary " +
        	    "FROM STUDENT s " +
        	    "JOIN STUDIRA st ON s.ID_STUD = st.ID_STUD " +
        	    "JOIN FAKULTET f ON st.ID_FAK = f.ID_FAK " +
        	    "JOIN SOBA r ON s.ID_SOB = r.ID_SOB " +
        	    "JOIN STUDENTSKIDOM d ON r.ID_STD = d.ID_STD " +
        	    "JOIN GRAD g ON d.ID_GR = g.ID_GR";


        try (Connection conn = ConnectionUtil_HikariCP.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                StudentFullDetailsDTO dto = new StudentFullDetailsDTO();
                //dto.setStudentId(rs.getInt("student_id"));
                dto.setStudentName(rs.getString("student_name"));
                dto.setFacultyName(rs.getString("faculty_name"));
                dto.setDormName(rs.getString("dorm_name"));
                dto.setRoomLabel(rs.getString("room_id"));  // ili getInt ako je broj
                dto.setCityName(rs.getString("city_name"));
                dto.setInventorySummary(rs.getString("inventory_summary"));

                result.add(dto);
            }
        }

        return result;
    }
    
    public List<StudentPaymentsDTO> fetchPaymentsWithDetails() throws SQLException {
        List<StudentPaymentsDTO> result = new ArrayList<>();

        String query = 
            "SELECT " +
            "  s.ime_stud || ' ' || s.prz_stud AS student_name, " +
            "  SUM(p.uk_iznos) AS total_payments, " +
            "  f.naz_fak AS faculty_name, " +
            "  d.naz_std AS dorm_name, " +
            "  r.id_sob AS room_label, " +
            "  g.naz_gr AS city_name " +
            "FROM student s " +
            "JOIN studira st ON s.id_stud = st.id_stud " +
            "JOIN fakultet f ON st.id_fak = f.id_fak " +
            "JOIN soba r ON s.id_sob = r.id_sob " +
            "JOIN studentskidom d ON r.id_std = d.id_std " +
            "JOIN grad g ON d.id_gr = g.id_gr " +
            "JOIN placanje p ON s.id_stud = p.id_stud " +
            "GROUP BY s.ime_stud, s.prz_stud, f.naz_fak, d.naz_std, r.id_sob, g.naz_gr " +
            "ORDER BY student_name";

        try (Connection conn = ConnectionUtil_HikariCP.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                StudentPaymentsDTO dto = new StudentPaymentsDTO();
                dto.setStudentName(rs.getString("student_name"));
                dto.setTotalPayments(rs.getBigDecimal("total_payments"));
                dto.setFacultyName(rs.getString("faculty_name"));
                dto.setDormName(rs.getString("dorm_name"));
                dto.setRoomLabel(rs.getString("room_label"));
                dto.setCityName(rs.getString("city_name"));

                result.add(dto);
            }
        }

        return result;
    }
}
