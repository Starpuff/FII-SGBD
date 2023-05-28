package ro.org.events.Repository.Interfaces;

import ro.org.events.Repository.Models.LocationModel;

public interface ILocationRepository
{
    String createLocation(String name, String address, int capacity, String description);

    LocationModel getLocation_byId(int id);

    LocationModel getLocation_byAddress(String address);

    String updateLocation(int id, String name, String address, int capacity, String description);

    String deleteLocation_byId(int id);

    String deleteLocation_byAddress(String address);

    String searchLocation_byDescription(String description);

    String getLocationsPaginated(int page, int size);
}
