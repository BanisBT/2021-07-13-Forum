package lt.tomasbarauskas.blog.entities;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
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

    @Column(name = "topic_text")
    private String topicText;

    @Column(name = "author")
    private String author;

    @OneToMany(mappedBy = "topic")
    private List<Comment> comments;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Tolerate
    public Topic() {
    }
}
