package com.company.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreSQLImplementation {
    public static final String driver = "org.postgresql.Driver";
    private final String url;
    private final String user;
    private final String password;

    public PostgreSQLImplementation() {
        this.url = "jdbc:postgresql://localhost:5432/casino";
        this.user = "postgres";
        this.password = "root";
    }

    public PostgreSQLImplementation(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public Connection getConnection() throws SQLException, Exception {
        Class.forName(PostgreSQLImplementation.driver).getDeclaredConstructor().newInstance();

        return DriverManager.getConnection(url, user, password);

    }

    public void close(Connection con) throws SQLException {
        if (con != null) {
            con.close();
        }
    }
}
