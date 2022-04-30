package structural.decorator.before;

public class Client {

    private CommentService commentService;

    public Client(CommentService commentService) {
        this.commentService = commentService;
    }

    public static void main(String[] args) { //컴파일 타임에 고정적임
        Client client = new Client(new SpamFilteringCommentService());
        client.writeComment("오징어게임");
        client.writeComment("보는게 하는거 보다 재밌을 수가 없지...");
        client.writeComment("http://whiteship.me");
    }

    private void writeComment(String comment) {
        commentService.addComment(comment);
    }


}
