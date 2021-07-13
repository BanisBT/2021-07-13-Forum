package lt.tomasbarauskas.blog.entities;

import java.time.LocalDateTime;

public class Comment {

    private Long id;

    private String text;

    private User user;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}