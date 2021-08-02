package lt.tomasbarauskas.blog.exceptions;

public class UserWithUsernameAlreadyExistException extends RuntimeException{

    private final String username;

    public UserWithUsernameAlreadyExistException(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
