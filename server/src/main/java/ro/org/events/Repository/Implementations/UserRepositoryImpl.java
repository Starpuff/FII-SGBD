package ro.org.events.Repository.Implementations;

import org.springframework.stereotype.Repository;
import ro.org.events.Repository.DatabaseConn;
import ro.org.events.Repository.Interfaces.UserRepository;
import ro.org.events.Repository.Models.UserModel;

import java.sql.*;

@Repository
public class UserRepositoryImpl implements UserRepository{
    @Override
    public String createUser(String username, String password, boolean is_admin) {
        String sql = "{? = call user_package.create_user(?,?,?)}";
        String message;

        try {
            CallableStatement callableStatement = DatabaseConn.getConnection().prepareCall(sql);
            callableStatement.setString(2, username);
            callableStatement.setString(3, password);
            callableStatement.setBoolean(4, is_admin);

            callableStatement.registerOutParameter(1, Types.VARCHAR);

            callableStatement.execute();

            message = callableStatement.getString(1);
            callableStatement.close();

            DatabaseConn.getConnection().close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return message;
    }


    @Override
    public UserModel getUser_byId(int id) {
        UserModel user = null;

        try (Connection conn = DatabaseConn.getConnection()) {
            CallableStatement stmt = conn.prepareCall("{CALL user_package.get_user_by_id(?)}");

            // Set input parameter
            stmt.setInt(1, id);

            // Execute the stored function
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                user = new UserModel();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setIsAdmin(rs.getBoolean("is_admin"));
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }


    @Override
    public UserModel getUser_byUsername(String username) {
        return null;
    }

    @Override
    public String updateUser(String username, String password, boolean is_admin) {
        return null;
    }

    @Override
    public String deleteUser(String username) {
        return null;
    }
}
