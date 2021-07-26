package lt.tomasbarauskas.blog.repositories;

import lt.tomasbarauskas.blog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public Optional<User> getUserByUsername(String username);
}
