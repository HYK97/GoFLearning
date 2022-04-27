# Factory Method 패턴이란 ?

- concrete creators(생산자)와 concrete product(제품)로 이루어져있는 패턴
- 클래스의 인스턴스를 만들때 서브클래스에서 결정하게 만드는 패턴

## Factory Method의 장단점
- 장점 : OCP 원칙을 잘 활용할 수 있다. 즉 확장에는 열려있고 변경에는 닫혀있는 것(기존의 코드를 변경하지 않고 새로운 기능들을 확장할 수 있기에 유지 보수성이 뛰어나다) 결합을 느슨하게 한다.
- 단점 : 클래스의 수가 많아진다
## 팩토리 메서드 패턴은 어떤 관계를 형성하고있을까?

### 기존의 클래스다이어그램

![](https://velog.velcdn.com/images/ddh963963/post/744097d8-b348-4ad1-97cb-5e200829a68a/image.png)

### 팩토리메서드 패턴 적용후 클래스다이어그램
        
![](https://velog.velcdn.com/images/ddh963963/post/bce28559-6756-47e9-90a5-edb7361a6ab3/image.png)

  
## 코드의 내용과 변경점?
### 기존의 orderShip

``` java
    public static Ship orderShip(String name, String email) {
        // validate
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("배 이름을 지어주세요.");
        }
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("연락처를 남겨주세요.");
        }

        prepareFor(name);

        Ship ship = new Ship();
        ship.setName(name);

        // Customizing for specific name
        if (name.equalsIgnoreCase("whiteship")) {
            ship.setLogo("\uD83D\uDEE5️");
        } else if (name.equalsIgnoreCase("blackship")) {
            ship.setLogo("⚓");
        }

        // coloring
        if (name.equalsIgnoreCase("whiteship")) {
            ship.setColor("white");
        } else if (name.equalsIgnoreCase("blackship")) {
            ship.setColor("black");
        }

        // notify
        sendEmailTo(email, ship);

        return ship;
    }
```
- 장점 : 클래스 하나로 사용 가능하다.

- 단점 : 먼저 새로운 클래스를 만들 때마다 새로운 로직을 기존 코드에 적용해야 하며 한 개의 파일에 내용이 길어지며 중복 요소가 존재한다.

### 변경후 orderShip


``` java

public interface ShipFactory {
    default Ship orderShip(String name, String email) {
        validate(name, email);
        prepareFor(name);
        Ship ship = createShip();
        sendEmailTo(email, ship);
        return ship;
    }
}
```
- 장점 : 보다 객체가 생성되는 과정을 보기 쉽고 각 단계가 의미하는 바를 정확하게 이해할 수 있다.

## 그럼 확장은 어떻게 ?
### 기존 확장 방법
``` java
public class ShipFactory {
    public static Ship orderShip(String name, String email) {
       .
       .
       .
        // coloring
        if (name.equalsIgnoreCase("whiteship")) {
            ship.setColor("white");
        } else if (name.equalsIgnoreCase("blackship")) {
            ship.setColor("black");
        } else if(name.equalsIgnoreCase("redship")){
           ship.setColor("red");
        }
        .
        .
        .

}
```
- 단점 : 새로운 빨간색 배를 공정에 추가할 때 이처럼 if 문이 계속 늘어남 이처럼 기존 코드를 변경하면서 새로운 기능을 추가할 수 있다.

### 변경후 확장 방법

``` java
public class WhiteShipFactory implements ShipFactory {
    @Override
    public Ship createShip() { // 만드는 방법 오버라이드
        return new WhiteShip();
    }
}

public class WhiteShip extends Ship{
    public WhiteShip() { //생성자로 배 기본정보 저장
        setName("whiteship");
        setLogo("\uD83D\uDEE5");
        setColor("white");
    }
}
```
- 장점 : 기존의 코드는 그대로 놔두고 새로운 클래스를 추가해서 확장시킨다. 각각의 코드가 간결해진다.
- 단점 : 클래스의 수가 증가한다.





## 실제로 어떻게 쓰일까 ?
    
- Spring의 beanFactory는 Factory Method 패턴으로 사용된다.
- Sigleton 패턴을 사용한 applicationContext 와는 다르게 beanFactory는 Factory Method 패턴을 이용하기 때문에 지연 로딩이 가능하므로 가벼운 컨테이너라는 이름으로도 불린다.

```java
     BeanFactory xmlFactory = new ClassPathXmlApplicationContext("context.xml");
     String hello1 = xmlFactory.getBean("hello", String.class);
     System.out.println("xmlFactory hello = " + hello1);
```    
