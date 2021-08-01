package lt.tomasbarauskas.blog.services;

import lt.tomasbarauskas.blog.entities.Comment;
import lt.tomasbarauskas.blog.repositories.CommentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Page<Comment> getAllCommentByTopicId(Long topicId, Pageable pageable) {
//        return commentRepository.getAllCommentByTopicId(topicId, pageable);
        return commentRepository.findAll(pageable);
    }
}
