package ro.org.events.Repository.Implementations;

import org.springframework.stereotype.Repository;
import ro.org.events.Repository.DatabaseConn;
import ro.org.events.Repository.Interfaces.IAuthRepository;
import ro.org.events.Repository.Models.UserModel;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class AuthRepositoryImpl implements IAuthRepository {
    @Override
    public int login_user(String username, String password) {

        String sql = "{? = call auth_page.check_credentials(?, ?)}";
        int login_status = 0;

        try(Connection conn = DatabaseConn.getConnection())
        {
            CallableStatement callableStatement = conn.prepareCall(sql);

            // Set input parameter
            callableStatement.setString(2, username);
            callableStatement.setString(3, password);

            // set output parameter
            callableStatement.registerOutParameter(1, java.sql.Types.INTEGER);

            // Execute the stored function
            callableStatement.execute();

            login_status = callableStatement.getInt(1);
            callableStatement.close();
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }


        return login_status;
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
