package rs.ac.uns.ftn.db.jdbc.dormitory.dao;

import java.sql.Connection;
import java.sql.SQLException;

import rs.ac.uns.ftn.db.jdbc.dormitory.model.Payment;

public interface PaymentDAO {
    void saveTransactional(Connection conn, Payment payment) throws SQLException;

    void updateTransactional(Connection conn, Payment payment) throws SQLException;

    boolean deleteByIdTransactional(Connection conn, int paymentId) throws SQLException;
    
    boolean existsById(Connection conn, int studentId) throws SQLException;
}
