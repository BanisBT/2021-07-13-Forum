package lt.tomasbarauskas.blog.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "topic")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "topic_tittle")
    private String topicTitle;

    @Column(name = "topic")
    private String topic;

    @Column(name = "author")
    private String author;

    @Transient
    private User user;

    @Transient
    private List<Comment> comments;
}
