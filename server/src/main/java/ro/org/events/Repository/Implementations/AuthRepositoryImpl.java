package ro.org.events.Repository.Implementations;

import org.springframework.stereotype.Repository;
import ro.org.events.Repository.DatabaseConn;
import ro.org.events.Repository.Interfaces.IAuthRepository;
import ro.org.events.Repository.Models.UserModel;

import java.sql.*;

@Repository
public class AuthRepositoryImpl implements IAuthRepository {
    @Override
    public String login_user(String username, String password) {
        String sql = "SELECT * FROM auth_page.check_credentials(?, ?)";
        String jsonResult = null;

        try (Connection conn = DatabaseConn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Set input parameters
            stmt.setString(1, username);
            stmt.setString(2, password);

            // Execute the query
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                jsonResult = rs.getString(1);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return jsonResult;
    }


    @Override
    public UserModel register_user(String username, String password)
    {
        UserModel user = null;

        try(Connection conn = DatabaseConn.getConnection())
        {
            CallableStatement stmt = conn.prepareCall("{CALL auth_page.register_user(?, ?)}");

            // Set input parameter
            stmt.setString(1, username);
            stmt.setString(2, password);

            // Execute the stored function
            ResultSet rs = stmt.executeQuery();

            if(rs.next())
            {
                user = new UserModel();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setIsAdmin(rs.getBoolean("is_admin"));
            }

            rs.close();
            stmt.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return user;
    }
}
