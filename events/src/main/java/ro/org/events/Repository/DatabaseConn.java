package ro.org.events.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConn {
    private static final String url = "jdbc:postgresql://localhost:5432/events";
    private static final String username = "postgres";
    private static final String password = "admin";

    private static Connection conn = null;

    public static Connection getConnection()
    {
        if(conn==null)
        {
            try {
                conn = DriverManager.getConnection(url, username, password);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return conn;
    }
}
