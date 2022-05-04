# Flyweight 패턴이란

#### Flyweight 패턴이란 객체 중 자주 사용되는 객체를 캐싱 해서 new 연산자의 사용을 줄여 메모리 사용을 줄이는 패턴이다. 즉 어떤 객체 중 자주 변하는 속성과 그렇지 않은 속성을 분리해서 자주 변하지 않는 것들은 모아서 재사용한다.


## Flyweight 장단점

- 장점 : 애플리케이션에서 사용하는 메모리를 줄일수 있다

- 단점 : 코드의 복잡도가 증가한다



## Flyweight 클래스 다이어그램
클라이언트는 Flyweight를 사용하기위해서 FlyweightFactory를 이용해서 실제 객체를 사용하게 된다.

![](https://velog.velcdn.com/images/ddh963963/post/42dced8e-9c95-421b-8196-b1aad386a8f2/image.png)

> 출처 : https://ducmanhphan.github.io/2020-02-28-Flyweight-pattern/



예시

- Charactor 객체의 속성중에 자주 변하는 속성을 Flyweight 객체인 Font로 빼서 캐싱하여 사용함.
  ![](https://velog.velcdn.com/images/ddh963963/post/b15e79a8-ee55-41fa-8dfb-b6f92aa6fc25/image.png)






## Flyweight 패턴 요소


- Flyweight : Flyweight의 공통된 메서드를 정의하고 Factory에 제공하는 클래스(인터페이스)

- ConcreteFlyweight : 구체적인 Flyweight의 기능을 구현하고 실제로 사용될 객체
- FlyweightFactory : 해당 클래스를 사용해서 Flyweight의 인스턴스를 생성 또는 공유(캐싱)해주는 역할을 한다.
- Client : Factory를 이용해 Flyweight객체를 제공받는 자



## Flyweight 구현하기

### Element(Flyweight InterFace)


```java 

/**
 * Flyweight InterFace
 * */
public interface Element {// 글자속성에 필요한 공통 메서드

    void getInformation();
    
}



```


### Font(Flyweight 구현체Concrete)


```java 

/**
 * flyWeight Concrete 클래스
 * 불변객체여야함
 */
@Getter
@ToString
public final class Font implements Element{

    // 모든 필드를 final로 생성해야댐

    final String family;

    final int size;

    public Font(String family, int size) {
        this.family = family;
        this.size = size;
    }


    @Override
    public void getInformation() {
         System.out.println("FontFamily : "+ family + ", size : "+ size);
    }
}



```

### FontFactory (Flyweight Factory)


```java
/**
 * flyWeight Factory 객체
 */
public class FontFactory {
    private Map<String, Element> cache = new HashMap<>();


    /**
     * cache.containsKey(font) 캐쉬된 객차가 있으면 그객체를 리턴하고
     * 아니면 새로운 객체를 생성한후 리턴
     *
     * @param font
     * @return
     */
    public Element getFont(String font) {
        if (cache.containsKey(font)) {
            return cache.get(font);
        } else {
            String[] split = font.split(":");
            Element newFont = new Font(split[0], Integer.parseInt(split[1]));
            cache.put(font, newFont);
            return newFont;
        }

    }

}



```


### Client

```java

public class Client {

    public static void main(String[] args) {
    
        FontFactory fontFactory = new FontFactory();
        
		//Character 속성중 자주 변하는 속성인 Color , Name 속성 외에 
        //FontSize 및 FontFamily는 FlyWeight 패턴을 이용해 메모리 사용을 줄임
        Character c1 = new Character('a', "red", fontFactory.getFont("nanm:12"));
        Character c2 = new Character('b', "yellow", fontFactory.getFont("nanm:13"));
        Character c3 = new Character('c', "blue", fontFactory.getFont("nanm:12"));
        
        System.out.println("c1 = " + c1.toString());
        System.out.println("c2 = " + c2.toString());
        System.out.println("c3 = " + c3.toString());
        fontFactory.getFont("nanm:12").getInformation();

    }
}



```


## Flyweight 어떻게 사용될까?

- Java.lang의 Integer 클래스의 valueOf 메서드를 보면 특정 범위 안의 정수 값은 Caching 되어있는 배열의 값을 그대로 리턴해준다.

```java

        Integer i1 = Integer.valueOf(10000);
        Integer i2 = Integer.valueOf(10000);
		System.out.println(i1.equals(i2));
        
        //결과값 True

```

- Integer 클래스의 valueOf 메서드

```java
 @HotSpotIntrinsicCandidate
    public static Integer valueOf(int i) {
        return i >= -128 && i <= Integer.IntegerCache.high ? Integer.IntegerCache.cache[i + 128] : new Integer(i);
    }

```
