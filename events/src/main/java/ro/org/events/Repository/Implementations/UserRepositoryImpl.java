package ro.org.events.Repository.Implementations;

import org.springframework.stereotype.Repository;
import ro.org.events.Repository.DatabaseConn;
import ro.org.events.Repository.Interfaces.UserRepository;
import ro.org.events.Repository.Models.User;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

@Repository
public class UserRepositoryImpl implements UserRepository{
    @Override
    public String createUser(String username, String password, boolean is_admin) {
        String sql = "{? = call user_package.create_user(?,?,?)}";
        String message = "";

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
    public User getUser_byId(int id) {
        String sql = "{? = call user_package.get_user_by_id(?)}";
        User user = null;

        try {

            CallableStatement callableStatement = DatabaseConn.getConnection().prepareCall(sql);
            callableStatement.registerOutParameter(1, Types.JAVA_OBJECT);
            callableStatement.setInt(2, id);
            callableStatement.execute();

            ResultSet resultSet = (ResultSet) callableStatement.getObject(2);
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
            }

            resultSet.close();
            callableStatement.close();
            DatabaseConn.getConnection().close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return user;
    }



    @Override
    public User getUser_byUsername(String username) {
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
