package lt.tomasbarauskas.blog.services;

import lt.tomasbarauskas.blog.entities.Topic;
import lt.tomasbarauskas.blog.repositories.TopicRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicService {

    private final TopicRepository topicRepository;

    public TopicService(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    public List<Topic> getAllTopic(){
        return topicRepository.findAll();
    }
}
