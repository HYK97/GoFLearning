package behavioral.interpreter.after;

import java.util.Map;

public interface PostfixExpression {

    /**
     * class를 늘리지 않고 Lambda Expressions를 이용한 확장방법
     */
/*
    static PostfixExpression plus(PostfixExpression left, PostfixExpression right) {
        return context -> left.interpret(context) + right.interpret(context);
    }

    static PostfixExpression minus(PostfixExpression left, PostfixExpression right) {
        return context -> left.interpret(context) - right.interpret(context);
    }

    static PostfixExpression variable(Character c) {
        return context -> context.get(c);
    }
*/

    int interpret(Map<Character, Integer> context);
}
