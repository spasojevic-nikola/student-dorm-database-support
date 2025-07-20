package rs.ac.uns.ftn.db.jdbc.dormitory.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import rs.ac.uns.ftn.db.jdbc.dormitory.connection.ConnectionUtil_HikariCP;
import rs.ac.uns.ftn.db.jdbc.dormitory.dao.StudentDAO;
import rs.ac.uns.ftn.db.jdbc.dormitory.model.Student;

public class StudentDAOImpl implements StudentDAO {

    @Override
    public int count() throws SQLException {
        String sql = "SELECT COUNT(*) FROM student";
        try (Connection conn = ConnectionUtil_HikariCP.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
            return 0;
        }
    }

    @Override
    public boolean delete(Student student) throws SQLException {
        if (student == null) return false;
        return deleteById(student.getId());
    }

    @Override
    public int deleteAll() throws SQLException {
        String sql = "DELETE FROM student";
        try (Connection conn = ConnectionUtil_HikariCP.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            return ps.executeUpdate();
        }
    }

    @Override
    public boolean deleteById(Integer id) throws SQLException {
        if (id == null) return false;
        try (Connection conn = ConnectionUtil_HikariCP.getConnection()) {
            String sql = "DELETE FROM student WHERE id_stud = ?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, id);
                int affected = ps.executeUpdate();
                return affected > 0;
            }
        }
    }

    @Override
    public boolean existsById(Integer id) throws SQLException {
        if (id == null) return false;
        try (Connection conn = ConnectionUtil_HikariCP.getConnection()) {
            String sql = "SELECT 1 FROM student WHERE id_stud = ?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    return rs.next();
                }
            }
        }
    }

    @Override
    public Iterable<Student> findAll() throws SQLException {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT id_stud, ime_stud, prz_stud, pol_stud, tel_stud, adr_stud, id_sob FROM student";
        try (Connection conn = ConnectionUtil_HikariCP.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Student s = new Student();
                s.setId(rs.getInt("id_stud"));
                s.setFirstName(rs.getString("ime_stud"));
                s.setLastName(rs.getString("prz_stud"));
                s.setGender(rs.getString("pol_stud"));
                s.setPhone(rs.getString("tel_stud"));
                s.setAddress(rs.getString("adr_stud"));
                s.setRoomId(rs.getInt("id_sob"));
                students.add(s);
            }
        }
        return students;
    }

    @Override
    public Iterable<Student> findAllById(Iterable<Integer> ids) throws SQLException {
        List<Student> students = new ArrayList<>();
        for (Integer id : ids) {
            Student s = findById(id);
            if (s != null) {
                students.add(s);
            }
        }
        return students;
    }

    @Override
    public Student findById(Integer id) throws SQLException {
        if (id == null) return null;
        String sql = "SELECT id_stud, ime_stud, prz_stud, pol_stud, tel_stud, adr_stud, id_sob FROM student WHERE id_stud = ?";
        try (Connection conn = ConnectionUtil_HikariCP.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Student s = new Student();
                    s.setId(rs.getInt("id_stud"));
                    s.setFirstName(rs.getString("ime_stud"));
                    s.setLastName(rs.getString("prz_stud"));
                    s.setGender(rs.getString("pol_stud"));
                    s.setPhone(rs.getString("tel_stud"));
                    s.setAddress(rs.getString("adr_stud"));
                    s.setRoomId(rs.getInt("id_sob"));
                    return s;
                }
            }
        }
        return null;
    }

    @Override
    public boolean save(Student student) throws SQLException {
        if (student == null) return false;
        try (Connection conn = ConnectionUtil_HikariCP.getConnection()) {
            String sql = "INSERT INTO student (id_stud, ime_stud, prz_stud, pol_stud, tel_stud, adr_stud, id_sob) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, student.getId());
                ps.setString(2, student.getFirstName());
                ps.setString(3, student.getLastName());
                ps.setString(4, student.getGender());
                ps.setString(5, student.getPhone());
                ps.setString(6, student.getAddress());
                ps.setInt(7, student.getRoomId());
                int affected = ps.executeUpdate();
                return affected > 0;
            }
        }
    }

    @Override
    public int saveAll(Iterable<Student> entities) throws SQLException {
        int count = 0;
        try (Connection conn = ConnectionUtil_HikariCP.getConnection()) {
            String sql = "INSERT INTO student (id_stud, ime_stud, prz_stud, pol_stud, tel_stud, adr_stud, id_sob) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                for (Student student : entities) {
                    ps.setInt(1, student.getId());
                    ps.setString(2, student.getFirstName());
                    ps.setString(3, student.getLastName());
                    ps.setString(4, student.getGender());
                    ps.setString(5, student.getPhone());
                    ps.setString(6, student.getAddress());
                    ps.setInt(7, student.getRoomId());
                    count += ps.executeUpdate();
                }
            }
        }
        return count;
    }
    
    //methods for working with transactions
    @Override
    public void saveTransactional(Connection conn, Student student) throws SQLException {
        String sql = "INSERT INTO student (id_stud, ime_stud, prz_stud, pol_stud, tel_stud, adr_stud, id_sob) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, student.getId());
            ps.setString(2, student.getFirstName());
            ps.setString(3, student.getLastName());
            ps.setString(4, student.getGender());
            ps.setString(5, student.getPhone());
            ps.setString(6, student.getAddress());
            ps.setInt(7, student.getRoomId());
            ps.executeUpdate();
        }
    }

    @Override
    public void updateTransactional(Connection conn, Student student) throws SQLException {
        String sql = "UPDATE student SET ime_stud = ?, prz_stud = ?, pol_stud = ?, tel_stud = ?, adr_stud = ?, id_sob = ? WHERE id_stud = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, student.getFirstName());
            ps.setString(2, student.getLastName());
            ps.setString(3, student.getGender());
            ps.setString(4, student.getPhone());
            ps.setString(5, student.getAddress());
            ps.setInt(6, student.getRoomId());
            ps.setInt(7, student.getId());
            ps.executeUpdate();
        }
    }

    @Override
    public boolean deleteByIdTransactional(Connection conn, int studentId) throws SQLException {
        String sql = "DELETE FROM student WHERE id_stud = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, studentId);
            int rowsDeleted = ps.executeUpdate();
            return rowsDeleted > 0;
        }
    }


    @Override
    public boolean existsById(Connection conn, int studentId) throws SQLException {
        String sql = "SELECT 1 FROM student WHERE id_stud = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, studentId);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }
    
    @Override
    public Student findById(Connection conn, int studentId) throws SQLException {
        String sql = "SELECT id_stud, ime_stud, prz_stud, pol_stud, tel_stud, adr_stud, id_sob FROM student WHERE id_stud = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, studentId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Student s = new Student();
                    s.setId(rs.getInt("id_stud"));
                    s.setFirstName(rs.getString("ime_stud"));
                    s.setLastName(rs.getString("prz_stud"));
                    s.setGender(rs.getString("pol_stud"));
                    s.setPhone(rs.getString("tel_stud"));
                    s.setAddress(rs.getString("adr_stud"));
                    s.setRoomId(rs.getInt("id_sob"));
                    return s;
                }
                return null;
            }
        }
    }

}
