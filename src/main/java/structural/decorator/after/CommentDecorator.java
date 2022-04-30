package structural.decorator.after;

public class CommentDecorator implements CommentService {

    private CommentService commentService;

    public CommentDecorator(CommentService commentService) {
        this.commentService = commentService;
    }

    @Override //wrapee
    public void addComment(String comment) {
        commentService.addComment(comment);
    }
}
