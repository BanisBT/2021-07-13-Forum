package lt.tomasbarauskas.blog.services;

import lt.tomasbarauskas.blog.entities.User;
import lt.tomasbarauskas.blog.entities.UserRole;
import lt.tomasbarauskas.blog.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveOrUpdate() {
        User user = new User();
        user.setUsername("banis");
        user.setPassword("gers");
        user.setRole(UserRole.REGULAR);
        userRepository.save(user);
    }
}