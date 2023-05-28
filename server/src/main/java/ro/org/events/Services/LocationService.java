package ro.org.events.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.org.events.Repository.Implementations.LocationRepositoryImpl;

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
}
