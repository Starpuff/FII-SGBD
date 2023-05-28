package ro.org.events.Repository.Models;


import jakarta.persistence.*;

@Entity
@Table(name = "locations")
public class LocationModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;
    private int capacity;
    private String description;

    public void setId(int id) {
        this.id = (long) id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString()
    {
        if(id==0)
        {
            return null;
        }
        return "LocationModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", capacity=" + capacity +
                ", description='" + description + '\'' +
                '}';
    }
}
