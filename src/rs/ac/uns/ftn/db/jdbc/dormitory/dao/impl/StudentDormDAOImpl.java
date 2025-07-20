package rs.ac.uns.ftn.db.jdbc.dormitory.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import rs.ac.uns.ftn.db.jdbc.dormitory.connection.ConnectionUtil_HikariCP;
import rs.ac.uns.ftn.db.jdbc.dormitory.dao.StudentDormDAO;
import rs.ac.uns.ftn.db.jdbc.dormitory.model.StudentDorm;

public class StudentDormDAOImpl implements StudentDormDAO {

    @Override
    public int count() throws SQLException {
        String query = "SELECT COUNT(*) FROM studentskidom";
        try (Connection connection = ConnectionUtil_HikariCP.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            return resultSet.next() ? resultSet.getInt(1) : -1;
        }
    }

    @Override
    public boolean delete(StudentDorm entity) throws SQLException {
        return deleteById(entity.getId());
    }

    @Override
    public int deleteAll() throws SQLException {
        String query = "DELETE FROM studentskidom";
        try (Connection connection = ConnectionUtil_HikariCP.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            return preparedStatement.executeUpdate();
        }
    }

    @Override
    public boolean deleteById(Integer id) throws SQLException {
        String query = "DELETE FROM studentskidom WHERE id_std=?";
        try (Connection connection = ConnectionUtil_HikariCP.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() == 1;
        }
    }

    @Override
    public boolean existsById(Integer id) throws SQLException {
        try (Connection connection = ConnectionUtil_HikariCP.getConnection()) {
            return existsByIdTransactional(connection, id);
        }
    }

    private boolean existsByIdTransactional(Connection connection, Integer id) throws SQLException {
        String query = "SELECT * FROM studentskidom WHERE id_std=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.isBeforeFirst();
            }
        }
    }

    @Override
    public Iterable<StudentDorm> findAll() throws SQLException {
        String query = "SELECT id_std, naz_std, adr, kapac, id_gr FROM studentskidom";
        List<StudentDorm> dormList = new ArrayList<>();
        try (Connection connection = ConnectionUtil_HikariCP.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                dormList.add(new StudentDorm(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getInt(4),
                        resultSet.getInt(5)
                ));
            }
        }
        return dormList;
    }

    @Override
    public Iterable<StudentDorm> findAllById(Iterable<Integer> ids) throws SQLException {
        List<StudentDorm> dormList = new ArrayList<>();
        StringBuilder sb = new StringBuilder("SELECT id_std, naz_std, adr, kapac, id_gr FROM studentskidom WHERE id_std IN (");
        for (@SuppressWarnings("unused") Integer id : ids) {
            sb.append("?,");
        }
        sb.deleteCharAt(sb.length() - 1).append(")");

        try (Connection connection = ConnectionUtil_HikariCP.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sb.toString())) {

            int i = 0;
            for (Integer id : ids) {
                preparedStatement.setInt(++i, id);
            }

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    dormList.add(new StudentDorm(
                            resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getInt(4),
                            resultSet.getInt(5)
                    ));
                }
            }
        }
        return dormList;
    }

    @Override
    public StudentDorm findById(Integer id) throws SQLException {
        String query = "SELECT id_std, naz_std, adr, kapac, id_gr FROM studentskidom WHERE id_std = ?";
        StudentDorm dorm = null;
        try (Connection connection = ConnectionUtil_HikariCP.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    dorm = new StudentDorm(
                            resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getInt(4),
                            resultSet.getInt(5)
                    );
                }
            }
        }
        return dorm;
    }

    @Override
    public boolean save(StudentDorm entity) throws SQLException {
        try (Connection connection = ConnectionUtil_HikariCP.getConnection()) {
            return saveTransactional(connection, entity);
        }
    }

    @Override
    public int saveAll(Iterable<StudentDorm> entities) throws SQLException {
        int rowsSaved = 0;
        try (Connection connection = ConnectionUtil_HikariCP.getConnection()) {
            connection.setAutoCommit(false);

            for (StudentDorm entity : entities) {
                if (saveTransactional(connection, entity)) {
                    rowsSaved++;
                }
            }

            connection.commit();
        }
        return rowsSaved;
    }

    private boolean saveTransactional(Connection connection, StudentDorm entity) throws SQLException {
        String insertCommand = "INSERT INTO studentskidom (naz_std, adr, kapac, id_gr, id_std) VALUES (?, ?, ?, ?, ?)";
        String updateCommand = "UPDATE studentskidom SET naz_std=?, adr=?, kapac=?, id_gr=? WHERE id_std=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(
                existsByIdTransactional(connection, entity.getId()) ? updateCommand : insertCommand)) {

            int i = 1;
            preparedStatement.setString(i++, entity.getName());
            preparedStatement.setString(i++, entity.getAddress());
            preparedStatement.setInt(i++, entity.getCapacity());
            preparedStatement.setInt(i++, entity.getCityId());
            preparedStatement.setInt(i++, entity.getId());

            return preparedStatement.executeUpdate() == 1;
        }
    }
    
    @Override
    public List<StudentDorm> findByCityId(int cityId) throws SQLException {
        String query = "SELECT id_std, naz_std, adr, kapac, id_gr FROM studentskidom WHERE id_gr = ?";
        List<StudentDorm> dormList = new ArrayList<>();

        try (Connection connection = ConnectionUtil_HikariCP.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, cityId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    StudentDorm dorm = new StudentDorm(
                        resultSet.getInt("id_std"),
                        resultSet.getString("naz_std"),
                        resultSet.getString("adr"),
                        resultSet.getInt("kapac"),
                        resultSet.getInt("id_gr")
                    );
                    dormList.add(dorm);
                }
            }
        }
        return dormList;
    }

}
