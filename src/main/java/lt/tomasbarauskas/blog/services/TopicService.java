package lt.tomasbarauskas.blog.services;

import lt.tomasbarauskas.blog.entities.Topic;
import lt.tomasbarauskas.blog.exceptions.TopicNotFoundException;
import lt.tomasbarauskas.blog.repositories.TopicRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TopicService {

    private final TopicRepository topicRepository;

    public TopicService(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    public Page<Topic> getAllTopic(Pageable pageable) {
        return topicRepository.findAll(pageable);
    }

    public Topic getTopicById(Long id) {
        return topicRepository.getTopicById(id).orElseThrow(() -> new TopicNotFoundException(id));
    }

    public void deleteTopic(Topic topic) {
        topicRepository.delete(topic);
    }

    public Topic createTopic(Topic topic) {
        return topicRepository.save(topic);
    }
}
