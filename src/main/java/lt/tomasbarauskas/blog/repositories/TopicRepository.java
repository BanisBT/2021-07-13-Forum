package lt.tomasbarauskas.blog.repositories;

import lt.tomasbarauskas.blog.entities.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {
}
