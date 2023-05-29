package ro.org.events.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.org.events.Repository.Implementations.EventRepositoryImpl;
import ro.org.events.Repository.Models.EventModel;

import java.sql.Date;

@Service
public class EventService {

    private final EventRepositoryImpl eventRepository;
    @Autowired
    public EventService(EventRepositoryImpl eventRepository) {
        this.eventRepository = eventRepository;
    }

    public String createEvent(String name, Date date, String type, int ticket_price, String location_address) {
        return eventRepository.createEvent(name, date, type, ticket_price, location_address);
    }

    public EventModel getEvent_byId(int id) {
        return eventRepository.getEvent_byId(id);
    }

    public String updateEvent(int id, String name, Date date, String type, int ticket_price, String location_address) {
        return eventRepository.updateEvent(id, name, date, type, ticket_price, location_address);
    }

    public String deleteEvent(int id) {
        return eventRepository.deleteEvent(id);
    }

    public String getEventsPaginated(int page, int size) {
        return eventRepository.getEventsPaginated(page, size);
    }
}
