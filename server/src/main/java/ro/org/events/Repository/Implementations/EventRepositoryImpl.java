package ro.org.events.Repository.Implementations;

import org.springframework.stereotype.Repository;
import ro.org.events.Repository.DatabaseConn;
import ro.org.events.Repository.Interfaces.IEventRepository;
import ro.org.events.Repository.Models.EventModel;

import java.sql.*;

@Repository
public class EventRepositoryImpl implements IEventRepository {


    @Override
    public String createEvent(String name, Date date, String type, int ticket_price, String location_address) {
        String message = null;

        try(Connection conn = DatabaseConn.getConnection())
        {
            CallableStatement stmt = conn.prepareCall("{? = CALL events_package.create_event(?,?,?,?,?)}");

            //set input parameters
            stmt.setString(2, name);
            stmt.setDate(3, date);
            stmt.setString(4, type);
            stmt.setInt(5, ticket_price);
            stmt.setString(6, location_address);

            //register output parameter
            stmt.registerOutParameter(1, java.sql.Types.VARCHAR);


            stmt.execute();

            message = stmt.getString(1);
            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return message;
    }

    @Override
    public EventModel getEvent_byId(int id) {

        EventModel model = null;
        String sql = "SELECT * FROM events_package.get_event_by_id(?)";

        try(Connection conn = DatabaseConn.getConnection())
        {
            PreparedStatement stmt = conn.prepareStatement(sql);

            //set input parameters
            stmt.setInt(1, id);

            //execute the stored function
            ResultSet rs = stmt.executeQuery();

            if(rs.next())
            {
                model = new EventModel();

                model.setId(rs.getInt("id"));
                model.setName(rs.getString("name"));
                model.setDate(rs.getDate("date"));
                model.setType(rs.getString("type"));
                model.setTicket_price(rs.getInt("ticket_price"));
                model.setLocation_name(rs.getString("location_name"));
            }
            stmt.close();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return model;
    }

    @Override
    public String updateEvent(int id, String name, Date date, String type, int ticket_price, String location_address) {

        String message = null;

        try (Connection conn = DatabaseConn.getConnection()) {
            CallableStatement stmt = conn.prepareCall("{? = CALL events_package.update_event(?,?,?,?,?,?)}");

            //set input parameters
            stmt.setInt(2, id);
            stmt.setString(3, name);
            stmt.setDate(4, date);
            stmt.setString(5, type);
            stmt.setInt(6, ticket_price);
            stmt.setString(7, location_address);

            //register output parameter
            stmt.registerOutParameter(1, java.sql.Types.VARCHAR);

            stmt.execute();

            message = stmt.getString(1);
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return message;
    }
        @Override
    public String deleteEvent(int id) {

        String message = null;

        try (Connection conn = DatabaseConn.getConnection()) {
            CallableStatement stmt = conn.prepareCall("{? = CALL events_package.delete_event_by_id(?)}");

            //set input parameters
            stmt.setInt(2, id);

            //register output parameter
            stmt.registerOutParameter(1, java.sql.Types.VARCHAR);

            //register output parameter
            stmt.execute();

            message = stmt.getString(1);
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return message;
    }

    @Override
    public String getEventsPaginated(int page, int size) {

        String jsonResult = null;

        String sql = "SELECT * FROM events_package.get_events_paginated(?,?)";


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
