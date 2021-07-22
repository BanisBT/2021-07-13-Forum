package lt.tomasbarauskas.blog.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TopicNotFoundException extends RuntimeException{

    private final Long topicId;

    public TopicNotFoundException(Long topicId){
        this.topicId = topicId;
    }

    public Long getTopicId() {
        return topicId;
    }
}
