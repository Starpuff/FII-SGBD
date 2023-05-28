package ro.org.events.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.org.events.Repository.Implementations.AuthRepositoryImpl;
import ro.org.events.Repository.Models.UserModel;

@Service
public class AuthService {

    private final AuthRepositoryImpl authRepository;

    @Autowired
    public AuthService(AuthRepositoryImpl authRepository) {
        this.authRepository = authRepository;
    }

    public String login(String username, String password) {
        return authRepository.login_user(username, password);
    }

    public UserModel register(String username, String password) {
        return authRepository.register_user(username, password);
    }
}
