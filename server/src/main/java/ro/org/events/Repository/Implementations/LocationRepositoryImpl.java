package ro.org.events.Repository.Implementations;

import org.springframework.stereotype.Repository;
import ro.org.events.Repository.DatabaseConn;
import ro.org.events.Repository.Interfaces.ILocationRepository;
import ro.org.events.Repository.Models.LocationModel;

import java.sql.*;

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

        LocationModel location = null;

        try(Connection conn = DatabaseConn.getConnection())
        {

            CallableStatement stmt = conn.prepareCall("{CALL location_package.select_location_by_id(?)}");

            // Set input parameter
            stmt.setInt(1, id);

            // Execute the stored function
            ResultSet rs = stmt.executeQuery();

            if(rs.next())
            {
                location = new LocationModel();
                location.setId(rs.getInt("id"));
                location.setName(rs.getString("name"));
                location.setAddress(rs.getString("address"));
                location.setCapacity(rs.getInt("capacity"));
                location.setDescription(rs.getString("description"));
            }

            rs.close();
            stmt.close();

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return location;
    }

    @Override
    public LocationModel getLocation_byAddress(String address) {

        LocationModel location = null;

        try(Connection conn = DatabaseConn.getConnection())
        {

            CallableStatement stmt = conn.prepareCall("{CALL location_package.select_location_by_address(?)}");

            // Set input parameter
            stmt.setString(1, address);

            // Execute the stored function
            ResultSet rs = stmt.executeQuery();

            if(rs.next())
            {
                location = new LocationModel();
                location.setId(rs.getInt("id"));
                location.setName(rs.getString("name"));
                location.setAddress(rs.getString("address"));
                location.setCapacity(rs.getInt("capacity"));
                location.setDescription(rs.getString("description"));
            }

            rs.close();
            stmt.close();

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return location;
    }

    @Override
    public String updateLocation(int id, String name, String address, int capacity, String description) {

        String message = null;

        try(Connection conn = DatabaseConn.getConnection())
        {
            CallableStatement stmt = conn.prepareCall("{? = CALL location_package.update_location(?,?,?,?,?)}");

            //set input parameters
            stmt.setInt(2, id);
            stmt.setString(3, name);
            stmt.setString(4, address);
            stmt.setInt(5, capacity);
            stmt.setString(6, description);

            //register output parameter
            stmt.registerOutParameter(1, Types.VARCHAR);

            //execute the stored function
            stmt.execute();

            message = stmt.getString(1);
            stmt.close();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return message;
    }

    @Override
    public String deleteLocation_byId(int id) {

        String message = null;

        try(Connection conn = DatabaseConn.getConnection())
        {
            CallableStatement stmt = conn.prepareCall("{? = CALL location_package.delete_location_by_id(?)}");

            //set input parameters
            stmt.setInt(2, id);

            //register output parameter
            stmt.registerOutParameter(1, Types.VARCHAR);

            //execute the stored function
            stmt.execute();

            message = stmt.getString(1);
            stmt.close();

        }catch (SQLException exception)
        {
            throw new RuntimeException(exception);
        }

        return message;
    }

    @Override
    public String deleteLocation_byAddress(String address) {

        String message = null;

        try(Connection conn = DatabaseConn.getConnection())
        {
            CallableStatement stmt = conn.prepareCall("{? = CALL location_package.delete_location_by_address(?)}");

            //set input parameters
            stmt.setString(2, address);

            //register output parameter
            stmt.registerOutParameter(1, Types.VARCHAR);

            //execute the stored function
            stmt.execute();

            message = stmt.getString(1);
            stmt.close();

        }catch (SQLException exception)
        {
            throw new RuntimeException(exception);
        }

        return message;
    }

    @Override
    public String searchLocation_byDescription(String description) {

        String jsonResult = null;

        String sql = "SELECT * FROM location_package.search_by_description(?)";

        try (Connection conn = DatabaseConn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Set input parameters
            stmt.setString(1, description);

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
    public String getLocationsPaginated(int page, int size) {

        String jsonResult = null;

        String sql = "SELECT * FROM location_package.get_paginated_locations(?,?)";


        try (Connection conn = DatabaseConn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Set input parameters
            stmt.setInt(1, page);
            stmt.setInt(2, size);

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
}