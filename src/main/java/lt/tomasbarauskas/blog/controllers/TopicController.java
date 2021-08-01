package lt.tomasbarauskas.blog.controllers;

import lombok.extern.slf4j.Slf4j;
import lt.tomasbarauskas.blog.entities.Topic;
import lt.tomasbarauskas.blog.services.CommentService;
import lt.tomasbarauskas.blog.services.TopicService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequestMapping("topic/")
public class TopicController {

    private final TopicService topicService;

    private final CommentService commentService;

    public TopicController(TopicService topicService, CommentService commentService) {
        this.topicService = topicService;
        this.commentService = commentService;
    }

    @GetMapping("/{id}/view")
    public String getTopicById(@PageableDefault(size = 3) Pageable pageable,
                               @PathVariable(required = false) Long id, Model model) {
        model.addAttribute("topic", topicService.getTopicById(id));
        model.addAttribute("commentsPageable", commentService.getAllCommentByTopicId(id, pageable));
        return "topic/view";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/create")
    public String getTopic(Model model,HttpSession session) {
        model.addAttribute("topic", new Topic());
        return "topic/create";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}/edit")
    public String editTopic(@PathVariable Long id, Model model,
    HttpSession session) {
        model.addAttribute("topic", topicService.getTopicById(id));
        return "topic/edit";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}/delete")
    public String deleteTopic(@PathVariable Long id, Model model) {
        topicService.deleteTopic(topicService.getTopicById(id));
        return "redirect:/";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public String createTopic(Topic topic) {
        log.info("Topic {} has been created", topic);
        return "redirect:/topic/" + topicService.createTopic(topic).getId() + "/view";
    }
}
