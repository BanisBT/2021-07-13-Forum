package lt.tomasbarauskas.blog.entities;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "topic")
@Builder
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "topic_tittle")
    private String topicTitle;

    @Column(name = "topic")
    private String topicText;

    @Column(name = "author")
    private String author;

    @Transient
    private User user;

    @Transient
    private List<Comment> comments;

    @Tolerate
    public Topic() {
    }
}
