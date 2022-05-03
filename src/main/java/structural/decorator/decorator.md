# Decorator 패턴이란

### 기존 객체의 새로운 기능들을 동적으로 유연하게 확장할 수 있도록 도와주는 패턴


## Decorator 장단점

- 장점 :
  새로운 클래스를 생성하지 않고 기존의 기능을 그대로 조합이 가능하다 (단일책임 원칙,ocp,di 역전 원칙등...)

런타임에 동적으로 기능들을 조합하거나 변경할수있다.




- 단점 :
  데코레이터들을 조합할때 코드가 복잡해질 수 있다.





## Decorator 클래스 다이어그램
클라이언트는 Component를 사용해서 실제 객체를 사용하게 된다.

![](https://velog.velcdn.com/images/ddh963963/post/57dc5b90-ac09-46e1-959a-2804d7d104ac/image.png)


> 출처 : https://mrnamu.blogspot.com/2019/10/3-decorator-pattern.html


예시

- CommentService가 여기선 Component 이고 DefaultCommentService는 ConcreteComponent, CommentDecorator는 Decorator 그 하위에는 데코레이터를 구체적인 기능을 정의한 ConcreteDecorator 이다.
  ![](https://velog.velcdn.com/images/ddh963963/post/76a5502f-2504-449e-ae02-36245f029132/image.png)



## Decorator 패턴 요소
- Component : 기본 기능을 정의하는 클래스인 ConcreteComponent와 추가 기능을 뜻하는 Decorator의 공통 기능을 정의한 클래스(인터페이스)
  즉, 클라이언트는 Component의 정의된 기능을 통해 조작하게된다.

- ConcreteComponent : 기본 기능을 구현하는 클래스

- Decorator : 여러 구체적인 Decorator 의 공통기능을 정의 해둔 클래스


- ConcreteDecoratorA, ConcreteDecoratorB, ...  C ... :
  Decorator의 하위 클래스로 기본 기능에 추가되는 개별적인 기능을 정의 한 클래스
  ConcreteComponet 객체를 참조하고 Decorator 클래스가 Compent 클래스를 합성 하는 관계가 된다.


## Decorator 구현하기

### CommentService(Component)


```java 

public interface CommentService {

    void addComment(String comment);
}


```


### DefaultCommentService(ConcreteComponent)


```java 

public class DefaultCommentService implements CommentService {

    @Override
    public void addComment(String comment) {
        System.out.println(comment);
    }
}



```

### CommentDecorator(Decorator)

- Decorator는 단 하나의 wrappee라고 하는 Component 타입의 인스턴스 타입을 가지고 있다.
```java 

public class CommentDecorator implements CommentService {

    private CommentService commentService; //Wrappee

    public CommentDecorator(CommentService commentService) {
        this.commentService = commentService;
    }

    @Override 
    public void addComment(String comment) {
        commentService.addComment(comment);
    }
}

```

### TrimmingCommentDecorator(ConcreteDecorator)

- 여러기능 들중 Trim 기능을 가진 Decorator

```java 

public class TrimmingCommentDecorator extends CommentDecorator {
    public TrimmingCommentDecorator(CommentService commentService) {
        super(commentService);
    }

    @Override
    public void addComment(String comment) {
        super.addComment(trim(comment));
    }

    private String trim(String comment) {

        return comment.replace("...", "");
    }
}




```




## Decorator 어떻게 사용될까?

- ServerHttpRequestDecorator : Webflux HTTP 요청 데코레이터  
  ServerHttpResponseDecorator :   Webflux HTTP 응답 데코레이터
  ServerHttpRequest와 ServerHttpResponse를 커스터마이징하여 Webflux도 Servlet에서 사용하는 다양한 메서드를 사용할수 있음.


```java

   public class inSpring {
    public static void main(String[] args) {
        // 웹플럭스 http 요청 데코레이터터
        ServerHttpRequestDecorator request;
        // 응답 데코레이터터
        ServerHttpResponseDecorator response;
    }
}


```

