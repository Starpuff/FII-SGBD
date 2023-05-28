package ro.org.events.Repository.Implementations;

import ro.org.events.Repository.Interfaces.ILocationRepository;
import ro.org.events.Repository.Models.LocationModel;

public class LocationRepositoryImpl implements ILocationRepository {
    @Override
    public String createLocation(String name, String address, int capacity, String description) {
        return null;
    }

    @Override
    public LocationModel getLocation_byId(int id) {
        return null;
    }

    @Override
    public LocationModel getLocation_byAddress(String address) {
        return null;
    }

    @Override
    public String updateLocation(int id, String name, String address, int capacity, String description) {
        return null;
    }

    @Override
    public String deleteLocation_byId(int id) {
        return null;
    }

    @Override
    public String deleteLocation_byAddress(String address) {
        return null;
    }
}
