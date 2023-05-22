package org.example;

import org.example.DAOs.EventDAO;
import org.example.DAOs.LocationDAO;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try{
            var locations = new LocationDAO();
            locations.create("The Fillmore", "1805 Geary Blvd, San Francisco, CA 94115", 1250);
            locations.create("The Warfield", "982 Market St, San Francisco, CA 94102", 2300);
            locations.create("The Independent", "628 Divisadero St, San Francisco, CA 94117", 500);
            var events = new EventDAO();
            events.create("Pink Floyd Concert", "2023-10-10", "The Fillmore", "concert");
            events.create("Michael Jackson Concert", "2023-10-11", "The Warfield", "concert");
            events.create("The Beatles Concert", "2023-10-12", "The Independent", "concert");

            events.findAll().forEach(System.out::println);
            Database.getConnection().close();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }
}