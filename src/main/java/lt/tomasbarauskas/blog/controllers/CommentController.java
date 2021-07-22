package lt.tomasbarauskas.blog.controllers;

import lt.tomasbarauskas.blog.entities.Comment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("topic/")
public class CommentController {

//    @GetMapping
//    public String addComment(Model model) {
//        model.addAttribute("comment", new Comment());
//        return "/";
//    }
}
