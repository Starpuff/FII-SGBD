package ro.org.events.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.org.events.Services.UserService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
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

    @GetMapping("/users/id/{id}")
    public String getUser(@PathVariable("id") int id) {
        return userService.getUser_byId(id).toString();
    }

    @GetMapping("/users/username/{username}")
    public String getUser(@PathVariable("username") String username) {
        return userService.getUser_byUsername(username).toString();
    }

    @PutMapping("/users/{id}")
    public String updateUser(@PathVariable("id") int id,
                             @RequestParam("username") String username,
                             @RequestParam("password") String password,
                             @RequestParam("is_admin") boolean is_admin) {

        return userService.updateUser(id, username, password, is_admin);
    }

    @DeleteMapping("/users/id/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        return userService.deleteUser_byId(id);
    }

    @DeleteMapping("/users/username/{username}")
    public String deleteUser(@PathVariable("username") String username) {
        return userService.deleteUser_byUsername(username);
    }
}
