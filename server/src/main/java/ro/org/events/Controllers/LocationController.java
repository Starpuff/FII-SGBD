package ro.org.events.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.org.events.Services.LocationService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
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

    @GetMapping("/locations/id/{id}")
    public String getLocation(@PathVariable("id") int id) {
        return locationService.getLocation_byId(id).toString();
    }

    @GetMapping("/locations/address/{address}")
    public String getLocation(@PathVariable("address") String address) {
        return locationService.getLocation_byAddress(address).toString();
    }

    @PutMapping("/locations/{id}")
    public String updateLocation(@PathVariable("id") int id,
                                 @RequestParam("name") String name,
                                 @RequestParam("address") String address,
                                 @RequestParam("capacity") int capacity,
                                 @RequestParam("description") String description) {

        return locationService.updateLocation(id, name, address, capacity, description);
    }

    @DeleteMapping("/locations/id/{id}")
    public String deleteLocation(@PathVariable("id") int id) {
        return locationService.deleteLocation_byId(id);
    }

    @DeleteMapping("/locations/address/{address}")
    public String deleteLocation(@PathVariable("address") String address) {
        return locationService.deleteLocation_byAddress(address);
    }
}
