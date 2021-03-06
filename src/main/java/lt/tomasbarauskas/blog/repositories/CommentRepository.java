package lt.tomasbarauskas.blog.repositories;

import lt.tomasbarauskas.blog.entities.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    Page<Comment> getAllCommentByTopicId(Long topicId, Pageable pageable);
}
