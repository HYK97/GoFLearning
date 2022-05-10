package behavioral.interpreter.after;

import java.util.Map;

// terminal expression
public class PlusExpression implements PostfixExpression {

    private PostfixExpression left, right;

    public PlusExpression(PostfixExpression left, PostfixExpression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int interpret(Map<Character, Integer> context) {
        return left.interpret(context) + right.interpret(context);
    }
}
