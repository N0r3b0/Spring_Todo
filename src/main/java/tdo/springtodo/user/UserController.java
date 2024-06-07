package tdo.springtodo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

//    @GetMapping
//    public List<UserModel> getAllUsers() {
//        return userService.getAllUsers();
//    }
//
//    @PostMapping
//    public void addUser(@RequestBody UserModel user) {
//        userService.addUser(user);
//    }

    // Other user-related endpoints as needed
}
