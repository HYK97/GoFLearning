package behavioral.interpreter.apply;

import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

public class InSpring {
    public static void main(String[] args) {
        Book book = new Book("doong");
        ExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression("testMethod");
        Expression expression2 = parser.parseExpression("name");
        /**
         * Book의 name filed를 리턴 , testMethod 실행
         * */
        System.out.println("expression.getValue(book) = " + expression.getValue(book));
        System.out.println(expression2.getValue(book));
    }
}
