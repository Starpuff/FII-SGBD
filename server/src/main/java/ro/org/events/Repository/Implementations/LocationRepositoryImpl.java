package ro.org.events.Repository.Implementations;

import org.springframework.stereotype.Repository;
import ro.org.events.Repository.DatabaseConn;
import ro.org.events.Repository.Interfaces.ILocationRepository;
import ro.org.events.Repository.Models.LocationModel;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

@Repository
public class LocationRepositoryImpl implements ILocationRepository {
    @Override
    public String createLocation(String name, String address, int capacity, String description) {
        String sql = "{? = call location_package.create_location(?,?,?,?)}";
        String message;

        try (Connection conn = DatabaseConn.getConnection()) {
            CallableStatement callableStatement = conn.prepareCall(sql);

            callableStatement.setString(2, name);
            callableStatement.setString(3, address);
            callableStatement.setInt(4, capacity);
            callableStatement.setString(5, description);

            callableStatement.registerOutParameter(1, Types.VARCHAR);

            callableStatement.execute();

            message = callableStatement.getString(1);
            callableStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return message;
    }

    @Override
    public LocationModel getLocation_byId(int id) {
        return null;
    }

    @Override
    public LocationModel getLocation_byAddress(String address) {
        return null;
    }

    @Override
    public String updateLocation(int id, String name, String address, int capacity, String description) {
        return null;
    }

    @Override
    public String deleteLocation_byId(int id) {
        return null;
    }

    @Override
    public String deleteLocation_byAddress(String address) {
        return null;
    }
}
