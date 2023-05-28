package ro.org.events.Repository.Interfaces;

import ro.org.events.Repository.Models.UserModel;

public interface IAuthRepository {
    String login_user(String username, String password);
    UserModel register_user(String username, String password);
}
