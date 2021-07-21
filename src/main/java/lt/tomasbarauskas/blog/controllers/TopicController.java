package lt.tomasbarauskas.blog.controllers;

import lt.tomasbarauskas.blog.services.TopicService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/topic")
public class TopicController {

    private final TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @GetMapping("/{id}")
    public String getTopic(@PathVariable(required = false) Long id, Model model){
        model.addAttribute("topic", topicService.getTopicById(id));
        return "topic";
    }
}
