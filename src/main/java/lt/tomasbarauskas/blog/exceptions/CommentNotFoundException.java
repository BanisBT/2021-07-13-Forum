package lt.tomasbarauskas.blog.exceptions;

public class CommentNotFoundException extends RuntimeException{

    private final Long commentId;

    public CommentNotFoundException(Long commentId) {
        this.commentId = commentId;
    }

    public Long getCommentId() {
        return commentId;
    }
}
