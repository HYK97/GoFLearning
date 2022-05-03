# Composite 패턴이란

### 전체든 부분이든 동일 한 인터페이스를 통해서 기능을 사용할 수있게 해주는 패턴

## Composite 장단점

- 장점 :
  복잡한 트리구조를 좀 더 편리하게 사용이 가능하게 해준다.

  다형상과 재귀를 활용해서 코드를 유리하게 사용할 수있다.

  클라이언트 코드를 변경하지 않고 새로운 타입의 코드(기능)를 사용할 수 있다.




- 단점 :
  공통된 인터페이스를 정의 해야하기 때문에 억지로 객체들을 일반화 해야하는 경우가 생길 가능성이 있다.




## Composite 클래스 다이어그램
클라이언트는 Component를 이용해서 Composite과 Leaf의 공통 메서드를 사용할 수 있다

![](https://velog.velcdn.com/images/ddh963963/post/b4300f1d-0043-4ca4-8c74-707ab9a80421/image.png)

> 출처 : https://s1.md5.ltd/image/e561a304782336567faf97b1c2c8c452.png





## Composite 패턴 요소
- Component : Compsite나 Leaf의 상위 클래스(인터페이스)로서 공통 메서드를 전체에서 사용할수 있도록 공통 인터페이스를 정의 해야한다.

- Composite : 구체적인 부분에 해당하며 여러개의 Composite나 Leaf를 가질수있다. 즉 Composite는 그릇의 역할을 하고, 또 다른 그릇을 참조하거나 내용물 객체를 참조 할 수 있다.

- Leaf : 그릇에 담겨지는 객체로서, Composite 부품으로 활용된다. 즉 Composite는Component의 형태로 Leaf를 활용한다.


- 정리 : Composite 와 Leaf를 트리구조로 이용하여 Component를 이용해서 클라이언트는 해당하는 공통 메서드를 사용할 수 있다.

## Composite 구현하기

### Component

- Compostie 와 Leaf 의 공통 메서드 인 getPrice를 인터페이스에 정의

```java 

public interface Component {

    int getPrice();

}


```


### Bag(Composite)


```java 

public class Bag implements Component {

    private List<Component> components = new ArrayList<>();

    public void add(Component component) {
        components.add(component);
    }

    public List<Component> getComponent() {
        return components;
    }

    //공통기능
    @Override
    public int getPrice() {
        return components.stream().mapToInt(Component::getPrice).sum();
    }
}

```

### Item(Leaf)
```java 

public class Item implements Component {

    private String name;

    private int price;

    public Item(String name, int price) {
        this.name = name;
        this.price = price;
    }


    //공통기능
    @Override
    public int getPrice() {
        return this.price;
    }
}

```

### Client

- Component를 이용해서 getPrice를 호출 하면 item 혹은 Bag 타입에 상관없이 사용 가능하다.

```java 

public class Client {

    public static void main(String[] args) {
        Item doranBlade = new Item("도란검", 450);
        Item healPotion = new Item("체력 물약", 50);

        Bag bag = new Bag();
        bag.add(doranBlade);
        bag.add(healPotion);

        Client client = new Client();
        client.printPrice(doranBlade);
        client.printPrice(bag);
    }

    //하나의 component 인터페이스에 정의된 메서드를 이용해서 사용 가능
    private void printPrice(Component item) {
        System.out.println(item.getPrice());
    }


}



```






## Composite는 어떻게 사용될까?

- Java의 Swing이 Composite 패턴을 사용해서 JFrame이  버튼이나 필드를 담는 객체로써 사용 하는 등의 기능을 제공한다.


```java

    /**
     * JFrame -> Composite
     * 
     * JTextField ,JButton 등등 -> Leaf
     * 
     * java.awt.Component -> Component
     */

    public static void main(String[] args) {
        JFrame frame = new JFrame();


        JTextField textField = new JTextField();
        textField.setBounds(200, 200, 200, 40);
        frame.add(textField);

        JButton button = new JButton("click");
        button.setBounds(200, 100, 60, 40);
        button.addActionListener(e -> textField.setText("Hello Swing"));
        frame.add(button);

        frame.setSize(600, 400);
        frame.setLayout(null);
        frame.setVisible(true);


    }


```

