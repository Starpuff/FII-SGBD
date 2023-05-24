package ro.org.events.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.org.events.Services.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public String createUser(@RequestParam("username") String username,
                             @RequestParam("password") String password,
                             @RequestParam("is_admin") boolean is_admin) {

        return userService.createUser(username, password, is_admin);
    }

    @GetMapping("/users/{id}")
    public String getUser(@PathVariable("id") int id) {
        return userService.getUser_byId(id).toString();
    }
}
