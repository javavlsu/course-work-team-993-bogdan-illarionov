package com.company.dao;

import com.company.bean.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

public class UserDAO implements DAOClass<User> {
    private static final String DELETE = "DELETE FROM users WHERE id=?";
    private static final String FIND_ALL = "SELECT * FROM users ORDER BY id";
    private static final String FIND_BY_ID = "SELECT * FROM users WHERE id=?";
    private static final String INSERT = "INSERT INTO users(Login, Password, Email) VALUES(?, ?, ?)";
    private static final String UPDATE = "UPDATE users SET Login=?, Password=?, Email=? WHERE id=?";

    private final PostgreSQLImplementation implementation;

    public UserDAO(PostgreSQLImplementation impl) {
        implementation = impl;
    }

    @Override
    public void create(User entity) {
        Connection conn = null;

        try {
            conn = implementation.getConnection();

            try (var stmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, entity.getLogin());
                stmt.setString(2, entity.getPassword());
                stmt.setString(3, entity.getEmail());

                int result = stmt.executeUpdate();
                ResultSet rs = stmt.getGeneratedKeys();

                System.out.println("Info: Rows affected " + result);

                if (rs.next()) {
                    entity.setId(rs.getInt(1));

                    System.out.println("Info: Object added: " + entity);
                }
            } catch (SQLException e) {
                System.out.println("QueryError " + e.getErrorCode() + ": " + e.getMessage());
            }

            implementation.close(conn);
        } catch (SQLException e) {
            System.out.println("ConnectionError " + e.getErrorCode() + ": " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    @Override
    public void update(User entity) {
        Connection conn = null;

        try {
            conn = implementation.getConnection();

            try (var stmt = conn.prepareStatement(UPDATE)) {
                stmt.setString(1, entity.getLogin());
                stmt.setString(2, entity.getPassword());
                stmt.setString(3, entity.getEmail());
                stmt.setLong(4, entity.getId());

                stmt.executeUpdate();

                System.out.println("Info: Object updated: " + entity);
            } catch (SQLException e) {
                System.out.println("QueryError " + e.getErrorCode() + ": " + e.getMessage());
            }

            implementation.close(conn);
        } catch (SQLException e) {
            System.out.println("ConnectionError " + e.getErrorCode() + ": " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public void delete(long id) {
        Connection conn = null;

        try {
            conn = implementation.getConnection();

            try (var stmt = conn.prepareStatement(DELETE)) {
                stmt.setLong(1, id);

                int result = stmt.executeUpdate();

                System.out.println("Info: One row deleted " + result + " by id " + id);
            } catch (SQLException e) {
                System.out.println("QueryError " + e.getErrorCode() + ": " + e.getMessage());
            }

            implementation.close(conn);
        } catch (SQLException e) {
            System.out.println("ConnectionError " + e.getErrorCode() + ": " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public User getById(long id) {
        Connection conn = null;
        User movie = null;

        try {
            conn = implementation.getConnection();

            try (var stmt = conn.prepareStatement(FIND_BY_ID)) {
                stmt.setLong(1, id);

                ResultSet rs = stmt.executeQuery();
                int count = 0;
                if (rs.next()) {
                    movie = new User(rs.getString("login"), rs.getString("password"), rs.getString("email"));
                    movie.setId(id);
                    count++;
                }

                System.out.println("Info: Return " + count+" row by id " + id);
            } catch (SQLException e) {
                System.out.println("QueryError " + e.getErrorCode() + ": " + e.getMessage());
            }

            implementation.close(conn);
        } catch (SQLException e) {
            System.out.println("ConnectionError " + e.getErrorCode() + ": " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return movie;
    }

    @Override
    public List<User> getAll() {
        Connection conn = null;
        List<User> list = new LinkedList<User>();

        try {
            conn = implementation.getConnection();

            try (var stmt = conn.prepareStatement(FIND_ALL)) {
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    var movie = new User(rs.getString("login"), rs.getString("password"), rs.getString("email"));
                    movie.setId(rs.getLong("id"));

                    list.add(movie);
                }

                System.out.println("Info: Rows retuned " + list.size());
            } catch (SQLException e) {
                System.out.println("QueryError " + e.getErrorCode() + ": " + e.getMessage());
            }

            implementation.close(conn);
        } catch (SQLException e) {
            System.out.println("ConnectionError " + e.getErrorCode() + ": " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return list;
    }

}
