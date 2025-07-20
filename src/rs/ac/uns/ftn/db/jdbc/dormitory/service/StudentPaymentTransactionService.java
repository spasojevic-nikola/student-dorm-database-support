package rs.ac.uns.ftn.db.jdbc.dormitory.service;

import java.sql.Connection;
import java.sql.SQLException;

import rs.ac.uns.ftn.db.jdbc.dormitory.connection.ConnectionUtil_HikariCP;
import rs.ac.uns.ftn.db.jdbc.dormitory.dao.PaymentDAO;
import rs.ac.uns.ftn.db.jdbc.dormitory.dao.StudentDAO;
import rs.ac.uns.ftn.db.jdbc.dormitory.dao.impl.PaymentDAOImpl;
import rs.ac.uns.ftn.db.jdbc.dormitory.dao.impl.StudentDAOImpl;
import rs.ac.uns.ftn.db.jdbc.dormitory.model.Payment;
import rs.ac.uns.ftn.db.jdbc.dormitory.model.Student;

public class StudentPaymentTransactionService {

    private final PaymentDAO paymentDAO = new PaymentDAOImpl();
    private final StudentDAO studentDAO = new StudentDAOImpl();

    public void addPaymentWithStudent(Student student, Payment payment) throws SQLException {
        try (Connection conn = ConnectionUtil_HikariCP.getConnection()) {
            try {
                conn.setAutoCommit(false);

                studentDAO.saveTransactional(conn, student);

                // Save payment (assign student ID if needed)
                payment.setStudentId(student.getId());
                paymentDAO.saveTransactional(conn, payment);

                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        }
    }

    public void updatePaymentWithStudent(Student student, Payment payment) throws SQLException {
        try (Connection conn = ConnectionUtil_HikariCP.getConnection()) {
            try {
                conn.setAutoCommit(false);

                if (!studentDAO.existsById(conn, student.getId())) {
                    throw new SQLException("Student with ID " + student.getId() + " does not exist.");
                }

                if (!paymentDAO.existsById(conn, payment.getId())) {
                    throw new SQLException("Payment with ID " + payment.getId() + " does not exist.");
                }

                studentDAO.updateTransactional(conn, student);
                paymentDAO.updateTransactional(conn, payment);

                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        }
    }


    public void deletePaymentWithStudent(int studentId, int paymentId) throws SQLException {
        try (Connection conn = ConnectionUtil_HikariCP.getConnection()) {
            try {
                conn.setAutoCommit(false);

                // Delete payment first
                if (!paymentDAO.deleteByIdTransactional(conn, paymentId)) {
                    conn.rollback();
                    throw new SQLException("Payment with ID " + paymentId + " does not exist.");
                }

                // Delete student
                if (!studentDAO.deleteByIdTransactional(conn, studentId)) {
                    conn.rollback();
                    throw new SQLException("Student with ID " + studentId + " does not exist.");
                }

                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        }
    }


    public boolean studentExists(int studentId) throws SQLException {
        try (Connection conn = ConnectionUtil_HikariCP.getConnection()) {
            return studentDAO.existsById(conn, studentId);
        }
    }
    
    public Student getStudentById(int studentId) throws SQLException {
        return studentDAO.findById(studentId);
    }
}
