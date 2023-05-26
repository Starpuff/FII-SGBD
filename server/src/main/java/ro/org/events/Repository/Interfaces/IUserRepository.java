package ro.org.events.Repository.Interfaces;

import ro.org.events.Repository.Models.UserModel;

public interface IUserRepository {

    String createUser(String username, String password, boolean is_admin);

    UserModel getUser_byId(int id);

    UserModel getUser_byUsername(String username);
    String updateUser(int id, String username, String password, boolean is_admin);

    String deleteUser_byId(int id);

    String deleteUser_byUsername(String username);
}
