# Singleton 패턴이란 ?

### 객체의 인스턴스가 오직 1개만 생성되는 패턴을 의미함

## 싱글톤 객체 안전한 구현 방법 5가지!

1.Sychronized - 가장 간단하지만 약간의 성능 저하가 있음

2.Eager Initialization(이른 초기화 방법) - 생성 객체를 미리 만들어놓고 쓰지 않으면 앱 로딩 시 많은 자원을 소모함

3.Double Checked Locking - 동기화 작업을 인스턴스가 널인 경우에만 확인하도록 해서 리소스 소모를 줄이는 방법

4.Static Inner Class - 내부 클래스를 생성해서 인스턴스를 리턴받는방법 (권장)

5.EnumClass - EnumClass로 만드는방법 (권장)
        

  
## 직접 구현해보자!       

### 1.Sychronized

- 사용방법은 간단하게 동기화하고 싶은 부분에 synchronized를 추가하면 된다 여기서는 인스턴스가 생성 여부를 확인하는 부분에서 동시성 문제가 발생할 수 있기 때문에 다음과 같이 메서드 자체에 사용했다

```java
 private static Settings instance;
    public static synchronized Settings getInstanceSynchronized() {
        if (instance == null) {
            instance = new Settings();
        }
        return instance;
    }
```

### 2.Eager Initialization(이른 초기화)

- 사용방법은 다음과 같이 final 키워드를 사용해 재선언하지 못하도록 하고 앱 로딩 시에 미리 인스턴스를 만들어두어 접근 시 동시성 문제가 발생하지 않도록 방지한다

```java
 private static final Settings INSTANCE = new Settings();
    public static Settings getInstanceEager() {
        return INSTANCE;
    }
```





### 3.Double Checked Locking

- 사용방법은 volatile 키워드와 synchronized 키워드를 이용하며 java 1.5 미만으로는 작동하지 않는다.
여기서 처음 Sychronized 사용하는 방법과 다른 점은 매번 인스턴스를 반환할 때마다 동기화를 해서 자원을 소모하지 않고
인스턴스가 생성되지 않았을 경우이면서 동시성 문제가 발생했을 때만 동기화하도록 하여 자원낭비를 줄인다.

```java
  private static volatile Settings instance;

    public static synchronized Settings getInstanceDoubleCheck() {
        if (instance == null) {
            synchronized (Settings.class) {
                if (instance == null) {
                    instance = new Settings();
                }
            }
        }
        return instance;
    }
```


### 4.Static Inner Class

- 내부에 Hodler 클래스를 만들어서 관리하는 방법으로 클래스가 로드될 때 객체가 생성되기 때문에 멀티 스레드 환경에서도 안전하기 때문에 권장하는 방법이다.
하지만 자바 리플렉션을 이용하면 싱글톤 조건을 깰 수 있다.

```java
  private static class SettingsHolder {
    private static final Settings INSTANCE = new Settings();
  }

  public static Settings getInstanceInnerClass() {
    return SettingsHolder.INSTANCE;
  }
```

### 5.EnumClass

- 매우 간단하다 Enum 클래스로 만들어주면 되며 자바 리플렉션으로도 싱글톤을 깰 수 없다. 하지만 상속을 할 수 없거나 마찬가지로 앱 로딩 시에 생성되기 때문에 자원의 소모가 크다

```java
  public enum EnumSettings {

      INSTANCE;
  }
```    

     
## 실제로 어떻게 쓰일까 ?
    
- Spring의 ApplicationContext는 Sigleton 객체로 운영된다 따라서 Bean으로 등록한 객체를 getBean으로 호출하게 된다면 항상 같은 인스턴스를 제공받을 수 있다.

```java
  ApplicationContext applicationContext =new AnnotationConfigApplicationContext(Config.class);
  String hello = applicationContext.getBean("hello", Hello.class);
  String hello2 = applicationContext.getBean("hello", Hello.class);
  System.out.println(hello == hello2); //True 반환
```    
