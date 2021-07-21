package lt.tomasbarauskas.blog.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "text")
    private String text;

//    @ManyToOne
//    @JoinColumn(name = "user_table_id")
    @Transient
    private User user;

    //@Column(name = "created_at")
    @Transient
    private LocalDateTime createdAt;

    //@Column(name = "updated_at")
    @Transient
    private LocalDateTime updatedAt;
}
