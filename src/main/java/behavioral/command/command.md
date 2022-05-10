# Command 패턴이란

#### 실행될 기능 즉 객체의 행위를 캡슐화 하는 패턴으로 기능을 요청하는 호출자와 그기능을 실행하는 수신자 클래스 사이의 의존성을 제거하는 패턴이다.

<hr>

## Command 장단점

- 장점 : Invoker 의 코드를 바꾸지않고 excute 실행 메서드를 사용가능하다.


- 단점 : 코드의 복잡도가 증가한다 Command (기능)클래스가 많이 늘어난다.

<hr>

## Command 클래스 다이어그램


![](https://velog.velcdn.com/images/ddh963963/post/cfe0eb5c-21a5-494c-a363-9c39699d8905/image.png)

> 출처 : https://gmlwjd9405.github.io/2018/07/07/command-pattern.html


예시

- Button 은 커맨드를 이용해서 불이 키거나 게임을 시작하는 기능을 사용할 수 있다.

![](https://velog.velcdn.com/images/ddh963963/post/9238c4a9-0f15-4e55-bf0b-5b7679a25626/image.png)











<hr>


## Command 패턴 요소


- Invoker : Command로 부터 기능을 실행해 달라고 요청하는 호출클래스

- Command : ConcreteCommand의 실행 기능에 대한 인터페이스로 실행은 execute, 취소는 undo 같은 메서드로 선언해준다.

- ConcreteCommand : 실제로 실행되는 기능을 구현함


- Receiver : ConcreteCommand에서 기능을 구현하기위해 필요한 클래스


<hr>

## Command 구현하기

### Button(Invoker)


```java 

/**
 * Invoker 
 */
public class Button {
    private Command command;

    private Stack<Command> commands = new Stack<Command>();

    public Button() {
    }
	
    public void undo() {
        if (!commands.isEmpty()) {
            commands.pop().undo();
        }
    }
	// 커맨드의 인터페이스를 사용함.
    public void press(Command command) {
        command.execute();
        commands.push(command);
    }
}

```


### Command(Command)


```java 

/**
 * Command
 */
public interface Command {
	//Concrete Command의 공통 메서드 정의
    void execute();

    void undo();

}


```

### LightOnCommand (Concrete Command)


```java
/**
 * Concrete Command
 * 불키는 동작을 하는 구체 커맨드
 * */
public class LightOnCommand implements Command {

    private Light light;
	
    public LightOnCommand(Light light) {
        this.light = light;
    }
	//불킴
    @Override
    public void execute() {
        light.on();
    }
	//불끔
    @Override
    public void undo() {
        new LightOffCommand(new Light()).execute();
    }
}


```


### Light (Receiver) 

```java
/**
 * Receiver 
 * 기능을 하는 객체
 * */
public class Light {

    private boolean isOn;

    public void on() {
        System.out.println("불을 켭니다.");
        this.isOn = true;
    }

    public void off() {
        System.out.println("불을 끕니다.");
        this.isOn = false;
    }

    public boolean isOn() {
        return this.isOn;
    }
}


```

<hr> 

## Command 어떻게 사용될까?

### 자바
 - Runable이라는 Functional Interface가 있는데 이 클래스는 일종의 Command 로 사용자가 Runable이라는 클래스를 구현체(ConcreteCommand)로 만들어 ExecutorService를 이용해서 특정 갯수의 쓰레드로 사용자가 생성한 Runable 구현체를 실행하게 만들어주는 코드이다.
 -  여기서 Runable은 Command 사용자가 구현한 Runable 구현체는 ConcreteCommand 이고 ExecutorService는 invoker, submit에 참조되는 클래스는 Receiver이다.

```java 
public class inJava {
    public static void main(String[] args) {
        Light light = new Light();
        Game game = new Game();
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        /**
         * runnable 이 여기서 command interFace
         * functional InterFace
         * */
        executorService.submit(new Runnable() {
            @Override
            public void run() {

            }
        });
        executorService.submit(light::off); //Method Reference
        executorService.submit(() -> light.on());// Lambda Expressions
        executorService.shutdown();
    }
}

```

<hr>

### spring

- SimpleJdbcInsert 는 Insert쿼리를 빌더형식으로 만들수 있게 해주는 Command의 구현체라고 볼수있다.

```java
public class inSpring {

    private DataSource dataSource;

    public inSpring(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void add(Command command) {
        /**
         * SimpleJdbcInsert -> 일종의 Command 구현체
         * */
        SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource)
                .withTableName("command")
                .usingGeneratedKeyColumns("id");

        Map<String, Object> data = new HashMap<>();
        data.put("name", command.getClass().getSimpleName());
        data.put("when", LocalDateTime.now());
        insert.execute(data);
    }

}
```

