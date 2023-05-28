package ro.org.events.Services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.org.events.Repository.Implementations.UserRepositoryImpl;
import ro.org.events.Repository.Models.UserModel;

@Service
public class UserService {

    private final UserRepositoryImpl userRepository;

    @Autowired
    public UserService(UserRepositoryImpl userRepository) {
        this.userRepository = userRepository;
    }

    public String createUser(String username, String password, boolean isAdmin) {
        return userRepository.createUser(username, password, isAdmin);
    }

    public UserModel getUser_byID(int id) {
        return userRepository.getUser_byId(id);
    }

    public UserModel getUser_byUsername(String username) {
        return userRepository.getUser_byUsername(username);
    }

    public String updateUser(int id, String username, String password, boolean isAdmin) {
        return userRepository.updateUser(id, username, password, isAdmin);
    }
    public String deleteUser_byId(int id) {
        return userRepository.deleteUser_byId(id);
    }

    public String deleteUser_byUsername(String username) {
        return userRepository.deleteUser_byUsername(username);
    }
}
