package ro.org.events.Repository.Models;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "events")
public class EventModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private Date date;

    private String type;

    private double ticket_price;

    private String location_address;

    private String location_name;

    @Override
    public String toString()
    {
        if(id==0)
        {
            return null;
        }
        return "EventModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", type='" + type + '\'' +
                ", ticket_price=" + ticket_price +
                ", location_name='" + location_name + '\'' +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTicket_price(double ticket_price) {
        this.ticket_price = ticket_price;
    }

    public void setLocation_address(String location_address) {
        this.location_address = location_address;
    }

    public String getLocation_address() {
        return location_address;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    public String getType() {
        return type;
    }

    public double getTicket_price() {
        return ticket_price;
    }

    public String getLocation_name() {
        return location_name;
    }

    public void setLocation_name(String location_name) {
        this.location_name = location_name;
    }
}
