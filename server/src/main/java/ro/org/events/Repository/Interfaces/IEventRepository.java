package ro.org.events.Repository.Interfaces;

import ro.org.events.Repository.Models.EventModel;

import java.sql.Date;

public interface IEventRepository {
    String createEvent(String name, Date date, String type, int ticket_price, String location_address);

    EventModel getEvent_byId(int id);

    String updateEvent(int id, String name, Date date, String type, int ticket_price, String location_address);

    String deleteEvent(int id);

    String getEventsPaginated(int page, int size);
}
