package rs.ac.uns.ftn.db.jdbc.dormitory.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import rs.ac.uns.ftn.db.jdbc.dormitory.dao.PaymentDAO;
import rs.ac.uns.ftn.db.jdbc.dormitory.model.Payment;

public class PaymentDAOImpl implements PaymentDAO {

    @Override
    public void saveTransactional(Connection conn, Payment payment) throws SQLException {
        String sql = "INSERT INTO placanje (id_plac, uk_iznos, tip_pl, dat_plac, opis_pl, id_stud) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, payment.getId());
            ps.setDouble(2, payment.getTotalAmount());
            ps.setString(3, payment.getPaymentType());
            ps.setDate(4, payment.getPaymentDate());
            ps.setString(5, payment.getDescription());
            ps.setInt(6, payment.getStudentId());
            ps.executeUpdate();
        }
    }

    @Override
    public void updateTransactional(Connection conn, Payment payment) throws SQLException {
        String sql = "UPDATE placanje SET uk_iznos = ?, tip_pl = ?, dat_plac = ?, opis_pl = ? WHERE id_plac = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, payment.getTotalAmount());
            ps.setString(2, payment.getPaymentType());
            ps.setDate(3, payment.getPaymentDate());
            ps.setString(4, payment.getDescription());
            ps.setInt(5, payment.getId());
            ps.executeUpdate();
        }
    }

    @Override
    public boolean deleteByIdTransactional(Connection conn, int paymentId) throws SQLException {
        String sql = "DELETE FROM placanje WHERE id_plac = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, paymentId);
            int rowsDeleted = ps.executeUpdate();
            return rowsDeleted > 0;
        }
    }

    
    @Override
    public boolean existsById(Connection conn, int studentId) throws SQLException {
        String sql = "SELECT 1 FROM placanje WHERE id_plac = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, studentId);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }
}
