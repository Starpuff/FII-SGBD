package ro.org.events.Services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.org.events.Repository.Implementations.UserRepositoryImpl;

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
}
