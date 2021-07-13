package lt.tomasbarauskas.blog.entities;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {

    private Long id;

    private String username;

    private String password;

    private String name;

    private String surname;

    private String email;

    private Integer age;

    private UserRole role = UserRole.REGULAR;

    private Comment comment;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}