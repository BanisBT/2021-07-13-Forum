package lt.tomasbarauskas.blog.controllers;

import lt.tomasbarauskas.blog.entities.Topic;
import lt.tomasbarauskas.blog.services.TopicService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("topic/")
public class TopicController {

    private final TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @GetMapping("/{id}")
    public String getTopicById(@PathVariable(required = false) Long id, Model model) {
        model.addAttribute("topic", topicService.getTopicById(id));
        return "topic/view";
    }

    @GetMapping("/create")
    public String getTopic(Model model) {
        model.addAttribute("topic", new Topic());
        return "topic/create";
    }

    @PostMapping("/create")
    public String createTopic(Topic topic) {
        topicService.createTopic(topic);
        return "redirect:/";
    }
}
