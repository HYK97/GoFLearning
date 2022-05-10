# Abstract Factory 패턴이란 ?

- 서로 비슷한 여러 객체들을 구체적인 클래스에 의존하지 않고 만들어줄 수 있게 해주는 패턴

## Factory Method와 Abstract Factory 차이점?

- 1. Factory Method는 Product를 생성할 때 Factory에 의존적이다. 반면에 Abstract Factory는
Product를 Factory로부터 의존성을 제거한다


- 2. 관점이 다르다 Factory Method는 Factory에 자체를 구현하는 방법에 초점을 둔다면 Abstract Factory는 Factory를 사용하는 방법에 초점을 두는 것이다


- 3. 목적이 다르다 Factory Method는 구체적인 객체의 생성과정을 하위의 구체적인 클래스로 옮기는 것이라면 Abstract Factory는 관련 있는 여러 객체를 구체적인 클래스에 의존하지 않고 만들어 주는 것이 목적이다

## Abstract Factory 패턴은 어떤 관계를 형성하고있을까?

### 팩토리메서드 클래스다이어그램
        
![](https://velog.velcdn.com/images/ddh963963/post/bce28559-6756-47e9-90a5-edb7361a6ab3/image.png)

- Factory Method는 ShipFactory의 create 메서드 하나로 여러 Product를 생성한다.
클라이언트 <-> Factory의 관계를 생각한다.


### Abstract Factory 클래스다이어그램
        
![](https://velog.velcdn.com/images/ddh963963/post/85de3477-ce16-4b69-aae8-36034aa607f3/image.png)


- Abstract Factory는 PartsFactory가 내부의 여러 create 메서드를 통해서 Anchor,Wheel를 조립해 생성한다.
클라이언트 <-> Product의 관계를 생각한다.


  
## 코드의 내용
### 클라이언트

``` java
public class WhiteShipFactory implements ShipFactory {


    private ShipPartsFactory shipPartsFactory;

    public WhiteShipFactory(ShipPartsFactory shipPartsFactory) { //shipPartsFactory를 DI해줌
        this.shipPartsFactory = shipPartsFactory;
    }

    @Override
    public Ship createShip() {
        Ship ship = new WhiteShip();
        ship.setAnchor(shipPartsFactory.createAnchor());
        ship.setWheel(shipPartsFactory.createWheel());
        return ship;
    }
}

```

### Factory

``` java
public class WhiteShipPartsFactory implements ShipPartsFactory {

    @Override
    public Anchor createAnchor() {
        return new WhiteAnchor();
    }

    @Override
    public Wheel createWheel() {
        return new WhiteWheel();
    }
}

```

### Product

``` java
public class WhiteProWheel implements Wheel {
}
public class WhiteProAnchor implements Anchor {
}

```






## 실제로 어떻게 쓰일까 ?
    
- Spring의 Factory Bean은 복잡한 Bean을 생성할 때 사용한다 xml로 Factory를 등록하여 getBean으로 받을 때는 Factory로 생성된 인스턴스로 리턴 받는다.


### FactoryBean 구현체
```java
public class ShipFactory implements FactoryBean<Ship> {
    @Override
    public Ship getObject() throws Exception {
        Ship ship = new WhiteShip();
        ship.setName("whiteShip");
        return ship;
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }
}
```    
### Client

```java
ApplicationContext applicationContext = new ClassPathXmlApplicationContext("config.xml");
        Ship whiteShip = applicationContext.getBean("whiteShip", Ship.class);
```    


### Config.xml
```xml
    <bean id="whiteShip" class="create.abstract_factory.apply.ShipFactory">
    </bean>
```    
