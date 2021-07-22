package lt.tomasbarauskas.blog.controllers;

import lt.tomasbarauskas.blog.exceptions.CommentNotFoundException;
import lt.tomasbarauskas.blog.exceptions.TopicNotFoundException;
import lt.tomasbarauskas.blog.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class CustomControllerAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({TopicNotFoundException.class,
            UserNotFoundException.class,
            CommentNotFoundException.class})
    public String notFound(Exception e, WebRequest request, Model model) {
        if (e instanceof TopicNotFoundException) {
            model.addAttribute("topicId", ((TopicNotFoundException) e).getTopicId());
            return "error/topicNotFound";
        } else if (e instanceof UserNotFoundException) {
            model.addAttribute("userId", ((UserNotFoundException) e).getUserId());
            return "error/userNotFound";
        } else if (e instanceof CommentNotFoundException) {
            model.addAttribute("commentId", ((CommentNotFoundException) e).getCommentId());
            return "error/commentNotFound";
        }
        return "error";
    }
}
