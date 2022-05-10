package behavioral.interpreter.after;

import java.util.Map;

public class App {
    public static void main(String[] args) {
        PostfixExpression expression = PostfixParser.parse("xyz+-");
        //파서가 있어야함 파서를 하면 Expression이 나옴
        //Map은 일종의 context
        int interpret = expression.interpret(Map.of('x', 1, 'y', 2, 'z', 3));
        System.out.println(interpret);

    }
}
