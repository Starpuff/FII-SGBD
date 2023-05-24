package ro.org.events.Repository.Interfaces;

import ro.org.events.Repository.Models.User;

public interface UserRepository{

    public String createUser(String username, String password, boolean is_admin);

    public User getUser_byId(int id);

    public User getUser_byUsername(String username);
    public String updateUser(String username, String password, boolean is_admin);

    public String deleteUser(String username);
}
