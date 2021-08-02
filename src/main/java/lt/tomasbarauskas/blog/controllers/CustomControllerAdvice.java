package lt.tomasbarauskas.blog.controllers;

import lombok.extern.slf4j.Slf4j;
import lt.tomasbarauskas.blog.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class CustomControllerAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({TopicNotFoundException.class,
            UserNotFoundException.class,
            CommentNotFoundException.class,
            RoleNotFoundException.class,
            UserWithUsernameAlreadyExistException.class})
    public String notFound(Exception e, Model model) {
        if (e instanceof TopicNotFoundException) {
            log.error("Topic with id - {} was not found", ((TopicNotFoundException) e).getTopicId());
            model.addAttribute("topicId", ((TopicNotFoundException) e).getTopicId());
            return "error/topicNotFound";
        } else if (e instanceof UserNotFoundException) {
            log.error("User with username - {} was not found", ((UserNotFoundException) e).getUsername());
            model.addAttribute("username", ((UserNotFoundException) e).getUsername());
            return "error/userNotFound";
        } else if (e instanceof CommentNotFoundException) {
            log.error("Comment with id - {} was not found", ((CommentNotFoundException) e).getCommentId());
            model.addAttribute("commentId", ((CommentNotFoundException) e).getCommentId());
            return "error/commentNotFound";
        } else if (e instanceof RoleNotFoundException) {
            log.error("Role with name - {} not found", ((RoleNotFoundException) e).getName());
            model.addAttribute("roleName", ((RoleNotFoundException) e).getName());
            return "error/roleNotFound";
        } else if (e instanceof UserWithUsernameAlreadyExistException) {
            log.error("Username - {} already exist", ((UserWithUsernameAlreadyExistException) e).getUsername());
            model.addAttribute("username", ((UserWithUsernameAlreadyExistException) e).getUsername());
            return "error/usernameAlreadyExist";
        }
        return "error";
    }
}
