package ro.org.events.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.org.events.Repository.Implementations.LocationRepositoryImpl;
import ro.org.events.Repository.Models.LocationModel;

@Service
public class LocationService {
    private final LocationRepositoryImpl locationRepository;

    @Autowired
    LocationService(LocationRepositoryImpl locationRepository) {
        this.locationRepository = locationRepository;
    }

    public String createLocation(String name, String address, int capacity, String description) {
        return locationRepository.createLocation(name, address, capacity, description);
    }

    public LocationModel getLocation_byId(int id) {
        return locationRepository.getLocation_byId(id);
    }

    public LocationModel getLocation_byAddress(String address) {
        return locationRepository.getLocation_byAddress(address);
    }

    public String updateLocation(int id, String name, String address, int capacity, String description) {
        return locationRepository.updateLocation(id, name, address, capacity, description);
    }

    public String deleteLocation_byId(int id) {
        return locationRepository.deleteLocation_byId(id);
    }

    public String deleteLocation_byAddress(String address) {
        return locationRepository.deleteLocation_byAddress(address);
    }

    public String searchLocation_byDescription(String description) {
        return locationRepository.searchLocation_byDescription(description);
    }

    public String getLocationsPaginated(int page, int size) {
        return locationRepository.getLocationsPaginated(page, size);
    }
}
