package lt.tomasbarauskas.blog.controllers;

import lombok.extern.slf4j.Slf4j;
import lt.tomasbarauskas.blog.entities.User;
import lt.tomasbarauskas.blog.entities.UserRole;
import lt.tomasbarauskas.blog.services.TopicService;
import lt.tomasbarauskas.blog.services.UserService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping()
public class MainController {

    private final UserService userService;

    private final TopicService topicService;

    public MainController(UserService userService, TopicService topicService) {
        this.userService = userService;
        this.topicService = topicService;
    }

    @GetMapping()
    public String getTopics(@PageableDefault(size = 5) Pageable pageable, Model model) {
        model.addAttribute("topics", topicService.getAllTopic(pageable));
        return "home";
    }

    @GetMapping("register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @GetMapping("login")
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("login")
    public String login(@Valid User user, BindingResult bindingResult, Model model) {
        User existingUser = userService.getUserByUsername(user.getUsername());

        if (existingUser != null) {
            if (existingUser.getPassword().equals(user.getPassword())) {
                model.addAttribute("user", existingUser);
                return "redirect:/";
            } else {
                bindingResult.addError(new ObjectError("user", String.format("Password for user '%s' is incorrect", user.getUsername())));
                log.warn("Entered password for User - {} is incorrect", user);
            }
        } else {
            bindingResult.addError(new ObjectError("user", String.format("User with username '%s' does not exist", user.getUsername())));
            log.warn("User with username - {} does not exist", user.getUsername());
        }
        return "login";
    }

    @PostMapping("register")
    public String createUser(@Valid User user, BindingResult errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("user", user);
            log.info("Invalid user {}", user);
            return "register";
        }
        userService.createUser(user);
        log.info("User {} has been register", user);
        return "redirect:/";
    }
}
