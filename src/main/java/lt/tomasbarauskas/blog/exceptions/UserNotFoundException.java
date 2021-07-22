package lt.tomasbarauskas.blog.exceptions;

public class UserNotFoundException extends RuntimeException{

    private final Long userId;

    public UserNotFoundException(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }
}
