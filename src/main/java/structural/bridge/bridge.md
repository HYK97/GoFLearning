# Bridge 패턴이란

### 구현부에서 추상층을 분리하여 각자 독립적으로 변형이 가능하고 확장이 가능하여 독립적인 계층구조로 발전시킬수있는 패턴 즉 추상적인 계층과 그 계층의 기능을 구체적으로 표현하는 계층을 분리하는 패턴

## Bridge 장단점

- 장점 :
추상적인 코드를 코드를 구체적인 코드 변경없이도 확장이가능함(ocp)

  
   추상적인 코드와 구체적인 코드 분리할 수 있음
  

- 단점 :
 계층구조가 늘어서 복잡도증가한다.





## Bridge 클래스 다이어그램
특징은 클라이언트는 추상적인 계층구조만 사용 implementation 간접 사용
클라이언트가 추상화되어있는 abstraction 계층 만사용하고 


## 적용전 
![](https://velog.velcdn.com/images/ddh963963/post/1b7bd651-8b15-48c3-b712-c15d77c513c8/image.png)

- 챔피언(추상객체)에 대한 Skin(기능)에 대한 계층이 따로 분리 되어있지 않기때문에 추상객체 + 기능이 합쳐진 형태의 클래스를 새로운 챔피언 혹은 Skin이 나올때마다 추가해줘야하는 단점이있음

## 적용후
![](https://velog.velcdn.com/images/ddh963963/post/2eb804b0-0d10-48b4-934d-5f35c49cc703/image.jpg)

- 추상계층과 그 기능을 구현하는 구체적인 계층을 나누어 코드 변경없이 확장이 가능하다.



## Bridge 패턴 요소
- Abstraction : 추상 클래스를 인스턴스를 가지고 해당 인스턴스를 통해 기능부분의 메서드를 호출하는 요소

 

- RefindAbstraction : 추상 계층에서 새로운 부분을 확장한 클래스

 
 

- Implementor : Abstraction의 기능을 구현하기 위해 정의한 인터페이스

 

- ConcreteImplementor : 실제 기능을 구현한 클래스


- 정리 : abstraction은 상위의 추상적인 로직을 담고 있는 클래스이고, implementation은 특정 플랫폼에 맞추어진 구체적인 상태, 행동을 가지고 있는 부분이다.


## Bridge 구현하기

### Abstraction
```java 
public interface Champion {

    void move();

    void skillQ();

    void skillW();

    void skillE();

    void skillR();
}

```


### DefaultClass

- Champion클래스의 중복되는 abstraction method를 계층을 추가해서 하위 클래스들의 코드를 줄여주는 클래스

```java 

  public class DefaultChampion implements Champion {

    private Skin skin;

    private String name;

    public DefaultChampion(Skin skin, String name) {
        this.skin = skin;
        this.name = name;
    }

    @Override
    public void move() {
        System.out.printf("%s %s move\n", skin.getName(), this.name);
    }

    @Override
    public void skillQ() {
        System.out.printf("%s %s Q\n", skin.getName(), this.name);
    }

    @Override
    public void skillW() {
        System.out.printf("%s %s W\n", skin.getName(), this.name);
    }

    @Override
    public void skillE() {
        System.out.printf("%s %s E\n", skin.getName(), this.name);
    }

    @Override
    public void skillR() {
        System.out.printf("%s %s R\n", skin.getName(), this.name);
    }
}


```

### RefindAbstraction
```java 

public class 아리 extends DefaultChampion {
    public 아리(Skin skin) {
        super(skin, "아리");
    }
}

```

### Implementor
```java 

public interface Skin {
    String getName();
}


```


### ConcreteImplementor
```java 

public class PoolParty implements Skin {

    @Override
    public String getName() {
        return "PoolParty";
    }
}


```


### Client
```java 

public class Client {
    public static void main(String[] args) {
        Champion PoolParty = new 아리(new PoolParty());
        PoolParty.skillQ();
        PoolParty.skillR();
    }
}


```



## Bridge는 어떻게 사용될까?

- 여기서 driver은 Implementation이고 이외에 Connection,Statement, PreparedStatement ,ResultSet은 Abstaction에 해당한다.


```java
public class JdbcExample {

    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("org.h2.Driver");

        //driver ->  Implementation
        //Connection,Statement, PreparedStatement ,ResultSet -> 추상화 Abstraction

        try (Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "")) {

            String sql = "CREATE TABLE  ACCOUNT " +
                    "(id INTEGER not NULL, " +
                    " email VARCHAR(255), " +
                    " password VARCHAR(255), " +
                    " PRIMARY KEY ( id ))";

            Statement statement = conn.createStatement();
            statement.execute(sql);

            PreparedStatement statement1 = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

```


