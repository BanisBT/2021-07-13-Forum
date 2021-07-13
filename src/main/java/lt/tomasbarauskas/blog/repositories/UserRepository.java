package lt.tomasbarauskas.blog.repositories;

import lt.tomasbarauskas.blog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}