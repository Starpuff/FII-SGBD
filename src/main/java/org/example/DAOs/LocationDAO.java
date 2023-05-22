package org.example.DAOs;

import org.example.Database;
import org.example.Records.Location;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LocationDAO {
    public void create(String name, String address, int capacity) throws SQLException {
        Connection conn = Database.getConnection();
        try (PreparedStatement pstmt = conn.prepareStatement(
                "insert into locations (name, address, capacity) values (?, ?, ?)")) {
            pstmt.setString(1, name);
            pstmt.setString(2, address);
            pstmt.setInt(3, capacity);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    public Location findByName(String name) throws SQLException {
        Connection conn = Database.getConnection();
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select id from locations where name='" + name + "'")) {
            return rs.next() ? new Location(rs.getInt(1), name, rs.getString(2), rs.getInt(3)) : null;
        } catch (SQLException e) {
            System.err.println(e);
        }
        return null;
    }

    public List<Location> findAll() throws SQLException {
        Connection conn = Database.getConnection();
        List<Location> locations = new ArrayList<>();
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select * from locations")) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String address = rs.getString("address");
                int capacity = rs.getInt("capacity");
                locations.add(new Location(id, name, address, capacity));
            }
            return (long) locations.size() > 0 ? locations : null;
        } catch (SQLException e) {
            System.err.println(e);
        }
        return null;
    }
}
