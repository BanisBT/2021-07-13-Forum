package lt.tomasbarauskas.blog.exceptions;

public class RoleNotFoundException extends RuntimeException{

    private final String name;

    public RoleNotFoundException(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
