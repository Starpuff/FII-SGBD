package org.example.DAOs;

import org.example.Database;
import org.example.Records.Event;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EventDAO {
    public void create(String name, String dateString, String location, String type) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        Connection conn = Database.getConnection();
        try (PreparedStatement pstmt = conn.prepareStatement(
                "insert into events (name, date, location, type) values (?, ?, ?, ?)")) {
            date = dateFormat.parse(dateString);
            pstmt.setString(1, name);
            pstmt.setDate(2, (java.sql.Date) date);
            pstmt.setString(3, location);
            pstmt.setString(4, type);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e);
        } catch (ParseException e) {
            e.printStackTrace();
            return;
        }
    }

    public Event findByName(String name) throws SQLException {
        Connection conn = Database.getConnection();
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select id from events where name='" + name + "'")) {
            return rs.next() ? new Event(rs.getInt(1), name, rs.getDate(2), rs.getString(3), rs.getString(4)) : null;
        } catch (SQLException e) {
            System.err.println(e);
        }
        return null;
    }

    public List<Event> findAll() throws SQLException {
        Connection conn = Database.getConnection();
        List<Event> events = new ArrayList<>();
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select * from events")) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Date date = rs.getDate("date");
                String location = rs.getString("location_id");
                String type = rs.getString("type");
                events.add(new Event(id, name, date, location,type));
            }
            return (long) events.size() > 0 ? events : null;
        } catch (SQLException e) {
            System.err.println(e);
        }
        return null;
    }

    public List<Event> findByDate(Date date) throws SQLException
    {
        Connection conn = Database.getConnection();
        List<Event> events = new ArrayList<>();
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("select * from events where date ='" + date +"'")) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String location = rs.getString("location_id");
                String type = rs.getString("type");
                events.add(new Event(id, name, date, location,type));
            }
            return (long) events.size() > 0 ? events : null;
        } catch (SQLException e) {
            System.err.println(e);
        }
        return null;
    }

    public List<Event> findByType(String type) throws SQLException{
        Connection conn = Database.getConnection();
        List<Event> events = new ArrayList<>();
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("select * from events where type ='" + type +"'")) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Date date = rs.getDate("date");
                String location = rs.getString("location_id");
                events.add(new Event(id, name, date, location,type));
            }
            return (long) events.size() > 0 ? events : null;
        } catch (SQLException e) {
            System.err.println(e);
        }
        return null;
    }
}
