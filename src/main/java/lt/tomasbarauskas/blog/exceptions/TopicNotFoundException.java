package lt.tomasbarauskas.blog.exceptions;

public class TopicNotFoundException extends RuntimeException{

    private final Long topicId;

    public TopicNotFoundException(Long topicId){
        this.topicId = topicId;
    }

    public Long getTopicId() {
        return topicId;
    }
}
