package lt.tomasbarauskas.blog.controllers;

import lombok.extern.slf4j.Slf4j;
import lt.tomasbarauskas.blog.dtos.UserRegistrationDTO;
import lt.tomasbarauskas.blog.entities.User;
import lt.tomasbarauskas.blog.services.TopicService;
import lt.tomasbarauskas.blog.services.UserService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    public String getTopics(@PageableDefault(size = 5) Pageable pageable,
                            Model model) {
        model.addAttribute("topics", topicService.getAllTopic(pageable));
        return "home";
    }

    @GetMapping("register")
    public String register(Model model) {
        model.addAttribute("user", new UserRegistrationDTO());
        return "register";
    }

    @GetMapping("login")
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @GetMapping("logout")
    public String logout(HttpServletRequest request) throws ServletException {
        request.logout();
        return "redirect:/";
    }

    @PostMapping("login")
    public String login(@Valid User user, BindingResult bindingResult, HttpSession session) {
        User existingUser = userService.getUserByUsername(user.getUsername());

        if (existingUser != null) {
            if (existingUser.getPassword().equals(user.getPassword())) {
                session.setAttribute("user", existingUser);
                return "redirect:/";
            } else {
                bindingResult.addError(new ObjectError("user",
                        String.format("Password for user '%s' is incorrect", user.getUsername())));
                log.warn("Entered password for User - {} is incorrect", user);
            }
        } else {
            bindingResult.addError(new ObjectError("user",
                    String.format("User with username '%s' does not exist", user.getUsername())));
            log.warn("User with username - {} does not exist", user.getUsername());
        }
        return "login";
    }

    @PostMapping("register")
    public String createUser(@Valid UserRegistrationDTO userRegistrationDTO, BindingResult errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("user", userRegistrationDTO);
            log.info("Invalid user {}", userRegistrationDTO);
            return "register";
        }
        if (userRegistrationDTO.getPassword().equals(userRegistrationDTO.getPasswordConfirm())) {
            errors.addError(new ObjectError("user", "Password do not match"));
        }
        if (userService.createUser(userRegistrationDTO) == null) {
            errors.addError(new ObjectError("user",
                    String.format("User with username %s already exist", userRegistrationDTO.getUsername())));
            return "register";
        }

        userService.createUser(userRegistrationDTO);
        log.info("User {} has been register", userRegistrationDTO);
        return "redirect:/";
    }
}
