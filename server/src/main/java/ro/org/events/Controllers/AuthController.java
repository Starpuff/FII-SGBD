package ro.org.events.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import ro.org.events.Services.AuthService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://127.0.0.1:5500", methods = {RequestMethod.POST})
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                     @RequestParam String password) {

        return authService.login(username, password);
    }

    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String password) {

        return authService.register(username, password).toString();
    }
}
