package lt.tomasbarauskas.blog.controllers;

import lt.tomasbarauskas.blog.entities.User;
import lt.tomasbarauskas.blog.entities.UserRole;
import lt.tomasbarauskas.blog.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping()
public class MainController {

    private final UserService userService;

    public MainController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String mainPage() {
        return "home";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("register")
    public String createUser(User user) {
        user.setRole(UserRole.REGULAR);
        userService.createUser(user);
        return "redirect:/";
    }
}
