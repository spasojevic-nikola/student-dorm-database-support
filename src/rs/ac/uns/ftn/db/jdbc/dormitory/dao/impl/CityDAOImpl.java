package rs.ac.uns.ftn.db.jdbc.dormitory.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import rs.ac.uns.ftn.db.jdbc.dormitory.connection.ConnectionUtil_HikariCP;
import rs.ac.uns.ftn.db.jdbc.dormitory.dao.CityDAO;
import rs.ac.uns.ftn.db.jdbc.dormitory.model.City;

public class CityDAOImpl implements CityDAO {

    private static final String TABLE_NAME = "grad";

    @Override
    public int count() throws SQLException {
        String query = "SELECT COUNT(*) FROM " + TABLE_NAME;
        try (Connection connection = ConnectionUtil_HikariCP.getConnection();
             PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            return rs.next() ? rs.getInt(1) : 0;
        }
    }

    @Override
    public boolean delete(City entity) throws SQLException {
        return deleteById(entity.getId());
    }

    @Override
    public int deleteAll() throws SQLException {
        String query = "DELETE FROM " + TABLE_NAME;
        try (Connection connection = ConnectionUtil_HikariCP.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            return ps.executeUpdate();
        }
    }

    @Override
    public boolean deleteById(Integer id) throws SQLException {
        String query = "DELETE FROM " + TABLE_NAME + " WHERE ID_GR = ?";
        try (Connection connection = ConnectionUtil_HikariCP.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            return ps.executeUpdate() == 1;
        }
    }

    @Override
    public boolean existsById(Integer id) throws SQLException {
        try (Connection connection = ConnectionUtil_HikariCP.getConnection()) {
            return existsByIdTransactional(connection, id);
        }
    }

    private boolean existsByIdTransactional(Connection connection, Integer id) throws SQLException {
        String query = "SELECT 1 FROM " + TABLE_NAME + " WHERE ID_GR = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    @Override
    public Iterable<City> findAll() throws SQLException {
        String query = "SELECT ID_GR, NAZ_GR, POS_BR, BR_ST FROM " + TABLE_NAME;
        List<City> cities = new ArrayList<>();
        try (Connection connection = ConnectionUtil_HikariCP.getConnection();
             PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                City city = new City(
                    rs.getInt("ID_GR"),
                    rs.getString("NAZ_GR"),
                    rs.getString("POS_BR"),
                    rs.getInt("BR_ST")
                );
                cities.add(city);
            }
        }
        return cities;
    }

    @Override
    public Iterable<City> findAllById(Iterable<Integer> ids) throws SQLException {
        List<City> cities = new ArrayList<>();
        StringBuilder sb = new StringBuilder("SELECT ID_GR, NAZ_GR, POS_BR, BR_ST FROM " + TABLE_NAME + " WHERE ID_GR IN (");
        int count = 0;
        for (@SuppressWarnings("unused") Integer id : ids) {
            sb.append("?,");
            count++;
        }
        if (count == 0) {
            return cities; // empty list
        }
        sb.deleteCharAt(sb.length() - 1).append(")");

        try (Connection connection = ConnectionUtil_HikariCP.getConnection();
             PreparedStatement ps = connection.prepareStatement(sb.toString())) {
            int i = 1;
            for (Integer id : ids) {
                ps.setInt(i++, id);
            }
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    City city = new City(
                        rs.getInt("ID_GR"),
                        rs.getString("NAZ_GR"),
                        rs.getString("POS_BR"),
                        rs.getInt("BR_ST")
                    );
                    cities.add(city);
                }
            }
        }
        return cities;
    }

    @Override
    public City findById(Integer id) throws SQLException {
        String query = "SELECT ID_GR, NAZ_GR, POS_BR, BR_ST FROM " + TABLE_NAME + " WHERE ID_GR = ?";
        try (Connection connection = ConnectionUtil_HikariCP.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new City(
                        rs.getInt("ID_GR"),
                        rs.getString("NAZ_GR"),
                        rs.getString("POS_BR"),
                        rs.getInt("BR_ST")
                    );
                }
            }
        }
        return null;
    }

    @Override
    public boolean save(City entity) throws SQLException {
        try (Connection connection = ConnectionUtil_HikariCP.getConnection()) {
            return saveTransactional(connection, entity);
        }
    }

    @Override
    public int saveAll(Iterable<City> entities) throws SQLException {
        int savedCount = 0;
        try (Connection connection = ConnectionUtil_HikariCP.getConnection()) {
            connection.setAutoCommit(false);

            for (City entity : entities) {
                if (saveTransactional(connection, entity)) {
                    savedCount++;
                }
            }

            connection.commit();
        }
        return savedCount;
    }

    private boolean saveTransactional(Connection connection, City entity) throws SQLException {
        String insertQuery = "INSERT INTO " + TABLE_NAME + " (ID_GR, NAZ_GR, POS_BR, BR_ST) VALUES (?, ?, ?, ?)";
        String updateQuery = "UPDATE " + TABLE_NAME + " SET NAZ_GR = ?, POS_BR = ?, BR_ST = ? WHERE ID_GR = ?";

        boolean exists = existsByIdTransactional(connection, entity.getId());

        try (PreparedStatement ps = connection.prepareStatement(exists ? updateQuery : insertQuery)) {
            if (exists) {
                int i = 1;
                ps.setString(i++, entity.getName());
                ps.setString(i++, entity.getPostalCode());
                ps.setInt(i++, entity.getPopulation());
                ps.setInt(i++, entity.getId());
            } else {
                int i = 1;
                ps.setInt(i++, entity.getId());
                ps.setString(i++, entity.getName());
                ps.setString(i++, entity.getPostalCode());
                ps.setInt(i++, entity.getPopulation());
            }
            return ps.executeUpdate() == 1;
        }
    }
}
