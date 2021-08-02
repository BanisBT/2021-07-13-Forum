package lt.tomasbarauskas.blog.services;

import lt.tomasbarauskas.blog.dtos.UserRegistrationDTO;
import lt.tomasbarauskas.blog.entities.User;
import lt.tomasbarauskas.blog.exceptions.RoleNotFoundException;
import lt.tomasbarauskas.blog.exceptions.UserNotFoundException;
import lt.tomasbarauskas.blog.repositories.RoleRepository;
import lt.tomasbarauskas.blog.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder encoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
    }

    public User createUser(UserRegistrationDTO userRegistrationDTO) {
        User user = new User(userRegistrationDTO);
        if (userRepository.getUserByUsername(user.getUsername()).isPresent()) {
            return null;
        }
        user.setRoles(Set.of((roleRepository.getRoleByName("USER").orElseThrow(() -> new RoleNotFoundException("USER")))));
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User getUserByUsername(String username) {
        return userRepository.getUserByUsername(username).orElse(null);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.getUserByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
    }
}
