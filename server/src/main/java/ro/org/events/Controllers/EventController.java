package ro.org.events.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.org.events.Services.EventService;

import java.sql.Date;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://127.0.0.1:5500", methods = {RequestMethod.POST})
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping("/events")
    public String createEvent(@RequestParam("name") String name,
                              @RequestParam("date") Date date,
                              @RequestParam("type") String type,
                              @RequestParam("ticket_price") int ticket_price,
                              @RequestParam("location_address") String location_address) {

        return eventService.createEvent(name, date, type, ticket_price, location_address);
    }

    @GetMapping("/events/id/{id}")
    public String getEvent(@PathVariable("id") int id) {
        return eventService.getEvent_byId(id).toString();
    }

    @PutMapping("/events/{id}")
    public String updateEvent(@PathVariable("id") int id,
                              @RequestParam("name") String name,
                              @RequestParam("date") Date date,
                              @RequestParam("type") String type,
                              @RequestParam("ticket_price") int ticket_price,
                              @RequestParam("location_address") String location_address) {

        return eventService.updateEvent(id, name, date, type, ticket_price, location_address);
    }

    @DeleteMapping("/events/id/{id}")
    public String deleteEvent(@PathVariable("id") int id) {
        return eventService.deleteEvent(id);
    }

    @PostMapping("/events/paginated")
    public String getEventsPaginated(int page, int size) {
        return eventService.getEventsPaginated(page, size);
    }

}
