package lt.tomasbarauskas.blog.entities;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "user_table")
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "email")
    private String email;

    @Column(name = "age")
    private Integer age;

//    @Enumerated(EnumType.STRING)
//    @Column(name = "role")
//    @Builder.Default
    private UserRole role = UserRole.REGULAR;

//    @OneToMany(mappedBy = "user")
    @Transient
    private List<Comment> comments;

    //@Column(name = "created_at")
    @Transient
    private LocalDateTime createdAt;

    //@Column(name = "updated_at")
    @Transient
    private LocalDateTime updatedAt;

    @Tolerate
    public User() {
    }
}
