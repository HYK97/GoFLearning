# Prototype 패턴이란

### 생성할 객체들의 타입이 프로토타입인 인스턴스(원본)로부터 결정되도록 하며, 새 객체를 만들기 위해 자신을 clone 방법을 이용하는 것을 프로토타입 패턴이라고 한다 

- 형태는 매개변수의 형과 Return 형이 다르게 나옴

## Prototype 장단점

- 장점 :

   객체를 생성할 때 복잡한 과정을 생략하고 보다 편리하게 복제해서 사용할 수 있다.(객체 생성의 비용을 줄일 수 있다)

    초기화 코드를 불필요하게 사용할 필요가 없다.

   생성되는 객체를 캡슐화하여 정보를 숨길 수 있다.



- 단점 :  
   
     clone() 메서드는 Object 클래스의 메서드로, 얕은 복사로 동작하기에 깊은 복제를 사용해야 할 경우 모든 객체의 clone() 메서드를 재정의 해야 한다는 단점이 있다.

   순환 참조가 있는 복잡한 객체를 복제하는 것은 어려울 수 있다.

## Prototype 구현하기
- 클라이언트는 -> 타깃 인터페이스만 사용하고

  Adaptee(기존 제품)과 target 과의 사이를 메꿔주는 Adapter를 구현하는 것이 목표

### 방법 1. Apaptee 코드를 수정할수있다면 target interface를 상속해서 구현하면 쉽게 할수있다.

```java
public class AccountService implements UserDetailsService { //target interface 상속

    public Account findAccountByUsername(String username) {
        Account account = new Account();
        account.setName(username);
        account.setPassword(username);
        account.setEmail(username);
        return account;
    }

    public void createNewAccount(Account account) {

    }

    public void updateAccount(Account account) {

    }

    @Override // 구현
    public UserDetails loadUser(String username) {
       return findAccountByUsername(username); //여기서 마찬가지로 Account 객체 또한 UserDetails를 상속해야함
    }
}
```
- 이런 식으로 각각의 타깃 인터페이스를 상속받아서 구현하면 된다
- 단점으로는 위에 말한 것과 같이 기존 코드를 수정해야 하고 단일 책임 원칙에 어긋난다


### 방법 2. target adaptee 코드 둘다 수정이 불가하다면 각각의 새로운 클래스를 만들고 target 인터페이스를 상속 받아서 구현한다.

```java
public class AccountUserDetailsService implements UserDetailsService {

    AccountService accountService;

    public AccountUserDetailsService(AccountService accountService) {
        this.accountService = accountService;
    }


    @Override
    public UserDetails loadUser(String username) {
        Account account = accountService.findAccountByUsername(username);
        return new AccountUserDetails(account);
    }
}

```
- 장점은 기존 코드를 건드리지 않고 새로운 클래스로 분리해 관리가 가능하다
- 단점으로는 클래스가 추가되기 때문에 복잡도가 증가한다


## Adapter는 어떻게 사용될까?

### java
- 먼저 자바에서는 자주 사용하는 Arrays.asList가 대표적인 예이다 이외에도 Enumeration나 File Io 에서도 자주쓰인다.



```java
    List<String> strings = Arrays.asList("1", "2", "3");
```




### Spring
- Spring에서는 앞서본 예시와 같이 Spring Security의 UserDetails 관련해서 사용자가 커스텀하여 사용한다.
