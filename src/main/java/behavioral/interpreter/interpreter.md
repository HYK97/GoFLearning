# Interpreter 패턴이란

#### 자주 해결해야할 문제들을 언어,문법,표현식 등과 같이 형태로 만들어주는 패턴으로 정규표현식,Shell 명령어 SQL 해석 등에 사용되는 패턴이다.

<hr>

## Interpreter 장단점

- 장점 : 자주 등장하는 문제 패턴을 언어와 문법으로 정의할 수 있다.
  기존코드를 변경하지 않고 새로운 Expression 추가가능


- 단점 : 복잡한 문법을 표현하려면 Expression과 Parser가 복잡해진다.

<hr>

## Interpreter 클래스 다이어그램


![](https://velog.velcdn.com/images/ddh963963/post/130d2399-8e97-43be-b2eb-77ef3793559c/image.png)





예시

- Parser를 통해서 Expression을 제공받아서 interpret를 실행

![](https://velog.velcdn.com/images/ddh963963/post/de710dae-6b64-4408-8cdd-29ea1cc0b039/image.png)











<hr>


## Interpreter 패턴 요소


- Expression(InterfFace) : interpret 메서드를 정의 한다.

- TerminalExpression : interpret(구문)의 마지막 로직을 담당하거나 받은데이터를 단순하게 리턴해주는기능을 수행한다.

- NonTerminalExpression : interpret의 각요소를 의미하고 체인 형태로 처리후 다른 Expression으로 전달한다.

- Context :  인터프리터가 구문해석을 실행하기 위한 정보를 제공하는 객체이다.

- Client : interpret 메소드 호출



<hr>

## Command 구현하기

### PostfixExpression(Expression)

- 다음과 같이 람다로 확장하거나 클래스를 생성해 interpret를 구현한다.

```java 

/**
 * Invoker 
 */
public interface PostfixExpression {

    /**
     * class를 늘리지 않고 Lambda Expressions를 이용한 확장방법
     * */
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
```


### PlusExpression(NonTerminalExpression)

- 덧셈기능을 하는 Expression

```java 

/**
 * NonTerminalExpression
 */
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



```

### PostfixParser

- stack을 이용해서 후위 연산을 한다.
  여기서 VariableExpression은 숫자를 리턴하거나 마지막 계산값을 리턴하는 TerminalExpression 이고 Plus/MinusExpression 는 중간의 계산값을 리턴하는 NonTerminalExpression 이다.

```java
public class PostfixParser {
    public static PostfixExpression parse(String expression) {
        Stack<PostfixExpression> stack = new Stack<>();
        for (char c : expression.toCharArray()) {
            stack.push(getExpression(c, stack));
        }
        return stack.pop();
    }

    private static PostfixExpression getExpression(char c, Stack<PostfixExpression> stack) {
        switch (c) {
            case '+':
                return new PlusExpression(stack.pop(), stack.pop());
            case '-':
                PostfixExpression right = stack.pop();
                PostfixExpression left = stack.pop();
                return new MinusExpression(left, right);
            default:
                return new VariableExpression(c);

        }
    }
    /**
     * class를 늘리지 않고 Lambda Expressions를 이용한 확장방법
     * */
  /*  private static PostfixExpression getExpression(char c, Stack<PostfixExpression> stack) {
        switch (c) {
            case '+':
                return plus(stack.pop(), stack.pop());
            case '-':
                PostfixExpression right = stack.pop();
                PostfixExpression left = stack.pop();
                return minus(left, right);
            default:
                return variable(c);

        }
    }*/
}



```


### VariableExpression (TerminalExpression)

- 값을 그대로 리턴

```java
/**
 * TerminalExpression
 * */
public class VariableExpression implements PostfixExpression {

    private Character variable;

    public VariableExpression(Character variable) {
        this.variable = variable;
    }

    @Override
    public int interpret(Map<Character, Integer> context) {
        return context.get(variable);
    }
}


```


### Client (Client,Context)

```java
/**
 * Client
 * */
public class Client {
    public static void main(String[] args) {
        PostfixExpression expression = PostfixParser.parse("xyz+-");
        //파서가 있어야함 파서를 하면 Expression이 나옴
        //Map은 일종의 context
        int interpret = expression.interpret(Map.of('x', 1, 'y', 2, 'z', 3));
        System.out.println(interpret);

    }
}


```

<hr> 

## Interpreter 어떻게 사용될까?

### 자바
- 자바에서는 다음과같이 정규 표현식으로 이용되고 있다.

```java 
public class InJava {

    public static void main(String[] args) {
        System.out.println(Pattern.matches(".pr...", "spring"));
        System.out.println(Pattern.matches("[a-z]{6}", "spring"));
        System.out.println(Pattern.matches("kkk[a-z]{6}[0-9]{4}", "kkkspring1234"));

    }
}

/** 결과값
 * true
 * true
 * true
 * */

```

<hr>

### spring

- ExpressionParser를 이용해서 특정 필드나 Method를 호출할 수 있다.

```java
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
```

