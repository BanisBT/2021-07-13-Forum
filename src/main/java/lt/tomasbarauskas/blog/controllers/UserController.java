package lt.tomasbarauskas.blog.controllers;

import lt.tomasbarauskas.blog.entities.User;
import lt.tomasbarauskas.blog.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
}
