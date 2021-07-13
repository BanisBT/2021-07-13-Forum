package lt.tomasbarauskas.blog.controllers;

import lt.tomasbarauskas.blog.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String hello() {
        userService.saveOrUpdate();
        return "Hello";
    }
}