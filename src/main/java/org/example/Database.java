package org.example;

import org.apache.commons.dbcp.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class Database {
    private static final String URL =
            "jdbc:postgresql://localhost:5432/events";
    private static final String USER = "postgres";
    private static final String PASSWORD = "password";

    private Database() {
    }

    public static Connection getConnection() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl(URL);
        dataSource.setUsername(USER);
        dataSource.setPassword(PASSWORD);

        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
