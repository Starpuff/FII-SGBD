package ro.org.events.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.org.events.Services.LocationService;

@RestController
@RequestMapping("/api")
public class LocationController {

    private final LocationService locationService;

    @Autowired
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @PostMapping("/locations")
    public String createLocation(@RequestParam("name") String name,
                                 @RequestParam("address") String address,
                                 @RequestParam("capacity") int capacity,
                                 @RequestParam("description") String description) {

        return locationService.createLocation(name, address, capacity, description);
    }
}
