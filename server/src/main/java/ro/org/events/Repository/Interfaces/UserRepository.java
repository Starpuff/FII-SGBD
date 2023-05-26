package ro.org.events.Repository.Interfaces;

import ro.org.events.Repository.Models.UserModel;

public interface UserRepository{

    public String createUser(String username, String password, boolean is_admin);

    public UserModel getUser_byId(int id);

    public UserModel getUser_byUsername(String username);
    public String updateUser(String username, String password, boolean is_admin);

    public String deleteUser(String username);
}
