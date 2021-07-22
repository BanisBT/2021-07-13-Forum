package lt.tomasbarauskas.blog.controllers;

import lt.tomasbarauskas.blog.entities.Topic;
import lt.tomasbarauskas.blog.exceptions.TopicNotFoundException;
import lt.tomasbarauskas.blog.services.TopicService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping()
public class TopicController {

    private final TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @GetMapping("/topic/{id}")
    public String getTopic(@PathVariable(required = false) Long id, Model model) {
        model.addAttribute("topic", topicService.getTopicById(id));
        return "topic";
    }

    @GetMapping("addTopic")
    public String getTopic(Model model) {
        model.addAttribute("topic", new Topic());
        return "addTopic";
    }

    //TODO nesupratu kodel iki sio metodo niekaip neateina info is formos apie Topic. Handler exception del
    @PostMapping("addTopic")
    public String createTopic(Topic topic) {
        topicService.createTopic(topic);
        return "redirect:/";
    }
}
