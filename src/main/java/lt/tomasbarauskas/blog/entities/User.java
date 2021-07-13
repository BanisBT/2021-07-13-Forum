package lt.tomasbarauskas.blog.entities;

import java.time.LocalDateTime;

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