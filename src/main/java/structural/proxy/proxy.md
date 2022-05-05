# Proxy 패턴이란

#### Proxy는 영어로 '대리인'이라는 뜻을 가지고 있다. 여기에서도 그뜻이 적용되어 대상객체가 아닌 Proxy객체가 대신 그일을 처리하는것이다. 그럼 원래 객체는 필요없는것이냐? 그건 아니다 Proxy가 실제로 일을 처리하는 것이 아닌 Proxy를 거치는 과정에서 원래 객체의 접근 제한 및 제어를 할 수있다.

<hr>

## Proxy 장단점

- 장점 : 기존 코드를 추가하지 않고 새로운 기능을 추가할 수 있다. (ocp)
  기존코드가 해야하는 일만 유지 할 수 있다.(단일 책임원칙)
  기능 추가 및 초기화 지연등으로 다양하게 활용할 수 있다.


- 단점 : 코드의 복잡도가 증가한다

<hr>

## Proxy 클래스 다이어그램




![](https://velog.velcdn.com/images/ddh963963/post/29d4cf33-39a0-4f11-aeeb-a082065119e4/image.png)


> 출처 : https://lktprogrammer.tistory.com/34



예시

- startGame 이라는 메서드 실행시 실제 게임시작에 대한 기능은 DefaultGameService(RealSubject)에 정의하고 startGame 메서드에 대한 추가기능은 GameServiceProxy(Proxy)에 정의한다.
  ![](https://velog.velcdn.com/images/ddh963963/post/02db302d-c57b-49f7-a6d9-80c08930c1c5/image.png)




<hr>


## Proxy 패턴 요소


- Proxy : RealSubject를 대리하는 요소(추가적인 부가기능을 정의하는 클래스)

- Client : proxy를 이용하는 요소



- Subject : proxy와 RealSubject에 필요한 공통 인터페이스를 정의한 클래스(인터페이스)




- RealSubject : 실제 구현된 클래스(핵심 기능)

<hr>

## Proxy  구현하기

### GameService(Subject)


```java 

/**
 * Subject
 * */
public interface GameService {

    void startGame();
    
}


```


### GameServiceProxy(Proxy)

- 상위 인터페이스가 있는 경우
```java 

/**
 * 인터페이스가 있는 경우(Proxy)
 */
public class GameServiceProxy implements GameService {

    private GameService gameService;

    public GameServiceProxy(GameService gameService) {
        this.gameService = gameService;
    }

    @Override
    public void startGame() {
        long before = System.currentTimeMillis();
        gameService.startGame();
        System.out.println("시간 = " + (System.currentTimeMillis() - before));
    }

    /**
     * Lazy initialization( 지연 초기화 )
     * 이때는 생성자가 없어야댐
     * */
    /*@Override
    public void startGame() {
        long before = System.currentTimeMillis();
        if (this.gameService == null) {
            this.gameService =new DefaultGameService();
        }
        gameService.startGame();
        System.out.println("시간 = " + (System.currentTimeMillis() - before));
    }*/
}

```
<hr>

- 인터페이스가 없거나 코드수정이 불가한 경우

```java

/**
 * 인터페이스가 없는 경우
 */
public class GamsServiceProxy extends GameService {

    @Override
    public void startGame() throws InterruptedException {
        long before = System.currentTimeMillis();
        super.startGame();
        System.out.println(System.currentTimeMillis() - before);
    }
}

```

### DefaultGameService (RealSubject)


```java
/**
 * RealSubject
 * */
public class DefaultGameService implements GameService {
    
    @Override
    public void startGame() {
        System.out.println("이 자리에 오신 여러분들을 환영합니다.");
    }
    
}


```


### Client

```java

public class Client {

    public static void main(String[] args) throws InterruptedException {
        
        GameService gameService = new GameServiceProxy(new DefaultGameService());
        gameService.startGame();
        
    }
    
}

```

<hr> 

## Proxy 어떻게 사용될까?

- 이전 코드는 Proxy가 Subject에 정의된 메서드를 각각 오버라이딩을 해야하지만 공통적인 부가기능을 여러곳에서 사용하기 편하게 하기위해서 다음과 같이Reflection을 이용하면 메소드를 모두 구현할 필요가 없다.

```java 
public class Java {

    public static void main(String[] args) {
        ProxyInJava proxyInJava = new ProxyInJava();
        proxyInJava.dynamicProxy();
    }

    private void dynamicProxy() {
        GameService gameServiceProxy = getGameServiceProxy(new DefaultGameService());
        // 다음과 같이 메서드 명으로 호출 해주면 Reflection을 통해서 해당메서드가 사용됌.
        gameServiceProxy.startGame();
        gameServiceProxy.endGame();
    }


    /**
     * 공통 부가기능은 모든메서드를 오버라이드할 필요없이 reflection을 이용해서 사용하면 간단하게 만들수 있다.
     *
     * @param target
     * @return
     */
    private GameService getGameServiceProxy(GameService target) {
        return (GameService) Proxy.newProxyInstance(this.getClass().getClassLoader(),
                new Class[]{GameService.class}, (proxy, method, args) -> {
                    System.out.println("start");
                    method.invoke(target, args);
                    System.out.println("end");
                    return null;
                });
    }
}

/* 결과

start
이 자리에 오신 여러분들을 환영합니다.
end
start
안녕히 가세요
end

*/

```

<hr>
- spring 에서 이용하는 @Aspect 과 @Around를 이용하면 여러클래스를 만들지않고도 등록한 빈에 일괄적으로 부가기능을 추가하여 사용할 수 있기 때문에 reflection 보다 훨씬 코드의 길이를 줄이면서 Proxy 기능을 사용할 수 있다.

- Proxy
```java
@Aspect
@Component
public class PerfAspect {


    /**
     * 시간을 측정하는 메서드
     * 여기서 ProceedingJoinPoint는 실행되는 메서드
     *
     * @param point
     * @throws Throwable
     */
    @Around("bean(gameService)")
    public void timestamp(ProceedingJoinPoint point) throws Throwable {
        long before = System.currentTimeMillis();
        point.proceed();
        System.out.println("point.getSignature().getName() = " + point.getSignature().getName());
        System.out.println(System.currentTimeMillis() - before);
    }
    
     @Around("bean(exService)")
    public void timestamp(ProceedingJoinPoint point) throws Throwable {
        long before = System.currentTimeMillis();
        point.proceed();
        System.out.println("point.getSignature().getName() = " + point.getSignature().getName());
        System.out.println(System.currentTimeMillis() - before);
    }
    
}
```

- Service Bean
```java
@Service
public class GameService {

    public void startGame() {
        System.out.println("이 자리에 오신 여러분을 진심으로 환영합니다.");
    }

    public void endGame() {
        System.out.println("안녕히 가세요");
    }


}
```
