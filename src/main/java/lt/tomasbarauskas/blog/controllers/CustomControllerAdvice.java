package lt.tomasbarauskas.blog.controllers;

import lombok.extern.slf4j.Slf4j;
import lt.tomasbarauskas.blog.exceptions.CommentNotFoundException;
import lt.tomasbarauskas.blog.exceptions.TopicNotFoundException;
import lt.tomasbarauskas.blog.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

@Slf4j
@ControllerAdvice
public class CustomControllerAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({TopicNotFoundException.class,
            UserNotFoundException.class,
            CommentNotFoundException.class})
    public String notFound(Exception e, WebRequest request, Model model) {
        if (e instanceof TopicNotFoundException) {
            log.error("Topic with id - {} was not found", ((TopicNotFoundException) e).getTopicId());
            model.addAttribute("topicId", ((TopicNotFoundException) e).getTopicId());
            return "error/topicNotFound";
        } else if (e instanceof UserNotFoundException) {
            log.error("User with id - {} was not found", ((UserNotFoundException) e).getUserId());
            model.addAttribute("userId", ((UserNotFoundException) e).getUserId());
            return "error/userNotFound";
        } else if (e instanceof CommentNotFoundException) {
            log.error("Comment with id - {} was not found", ((CommentNotFoundException) e).getCommentId());
            model.addAttribute("commentId", ((CommentNotFoundException) e).getCommentId());
            return "error/commentNotFound";
        }
        return "error";
    }
}
