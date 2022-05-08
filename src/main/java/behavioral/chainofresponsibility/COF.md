# Chain Of Responsibility(COR) 패턴이란

#### 요청을 보내는 쪽(sender)과 요청을 처리하는(receiver) 쪽을 분리하는 패턴으로 연속된 요청을 핸들러 체인을 통해서 처리한다. 즉 요청하는 쪽에서 처리하는쪽의 관심사를 분리하는것.


<hr>

## COR 패턴 장단점

- 장점 : client 코드를 변경하지 않고 체인 추가 (OCP)
  핸들러마다 자신이 해야할 코드 만 가지고있다. (SRP)
  체인을 원하는대로 다양하게 조립할 수 있다.

- 단점 : 디버깅하기 번거롭다.


<hr>

## COR 패턴 클래스 다이어그램

- 클라이언트 쪽에서 Handler를 의존해 연속적인 ConcreteHandler를 사용하게된다.
  ![](https://velog.velcdn.com/images/ddh963963/post/9d34a675-4d53-4d25-9f83-4023d8f0aa3b/image.png)

예시
- 클라이언트는 구체적인 기능을하는 핸들러에 의존적이지 않고 기능을 사용할 수 있다.
  ![](https://velog.velcdn.com/images/ddh963963/post/c16e5015-f6ae-448f-b47d-ba3434a3641f/image.png)


<hr>

## COR 패턴 요소

- client : Handler를 사용하는 객체 요청을 보낸다.

- Handler : ConcreteHandler 의 공통 인터페이스를 정의한 클래스.

- ConcreteHandler : 실제 Client가 사용하는 기능을 정의한 클래스로 Receiver(조립하는객체)가 조립하는 클래스

패턴 구현하기

<hr>

## COR 구현하기

### Client
- client는 추상화 계층인 RequestHandler만 바라보고 있으면 된다.
```java
public class Client {
    private RequestHandler requestHandler;

    public Client(RequestHandler requestHandler) {
        this.requestHandler = requestHandler;
    }

    //요청 하는쪽 -> 실제 클라이언트
    public void doWork() {//
        Request request = new Request("Request Body");
        requestHandler.handle(request);
    }

}
```
### RequestHandler(Handler)
- Handler들의 추상화 클래스
```java
public abstract class RequestHandler {
    private RequestHandler nextHandler;

    public RequestHandler(RequestHandler nextHandler) {
        this.nextHandler = nextHandler;
    }


    public void handle(Request request) {
    //다음 핸들러가 있을시에 실행하도록 함
        if (nextHandler != null) {
            nextHandler.handle(request);
        }
    }
}
```

### AuthRequestHandler(ConcreteHandler)
- RequestHandler의 구현체 실질적인 기능을 구현한다.
```java
public class AuthRequestHandler extends RequestHandler {


    public AuthRequestHandler(RequestHandler nextHandler) {
        super(nextHandler);
    }


    public void handle(Request request) {
        System.out.println("인증");
        super.handle(request);
    }
}

```

### 실행 결과

- client 는 Handler가 어떤식으로 구성되있는지 몰라도 된다 아래처럼 외부에서 체인을 만들어서 주입해주기 때문이다.
```java

public class Main {

    public static void main(String[] args) {
    
    	//핸들러 조립
        RequestHandler handler = new AuthRequestHandler(new LoggingRequestHandler(new PrintRequestHandler(null)));
        Client client = new Client(handler);
        client.doWork();
    }
    
    /**
    *인증
    *로깅
    *Request Body
    */

}
```

<hr>

## COR 패턴은 어떻게 사용될까?

### 자바
- 사용자가  어떤 웹페이지로 요청을 할때 그에 맞는 필터를 다음과같이 정의해서 필터를 거치도록 설정할 수 있다.

```java
@WebFilter(urlPatterns = "/hello") // 특정 url 서블릿에 접근할시 다음 필터를 적용
public class MyFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("게임 참여");
        chain.doFilter(request, response);
        System.out.println("게임 끝");
    }
}
```

### spring


- Spring Security를 사용할 때 적용할 필터를 다음과 같이 빌더형식으로 설정할 수 있다.
```java
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * 체인 필터를 설정하는 코드
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    //모든 페이지의 접근권한을 인증없이 허가
        http.authorizeRequests().anyRequest().permitAll();
        //http.authorizeRequests().anyRequest().permitAll().and().addFilter(new MyFilter());
    }
}

```



