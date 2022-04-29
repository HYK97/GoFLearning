# Adapter 패턴이란

### 기존 코드를 클라이언트가 사용하는 인터페이스의 구현체로 바꿔주는 패턴(상호호환 되도록 만들어주는 패턴)

- 형태는 매개변수의 형과 Return 형이 다르게 나옴

## Adapter 장단점

- 장점 : 기존 코드를 변경하지 않고 인터페이스 구현체를 만들어서 재사용이 가능함(Ocp)

  코드가 하던 일과 특정 인터페이스 구현체로 변환하는 작업을 각기 다른 클래스로 분리하고 관리할 수 있음. (단일 책임 원칙)


- 단점 : 클래스 증가로 복잡도 증가 따라서 경우에 따라서 기존 코드를 수정하는 것이 좋을 수도 있음

## Adapter 클래스 다이어그램


### Adapter 패턴 적용전
![](https://velog.velcdn.com/images/ddh963963/post/514da67c-4233-44bf-83a5-8185a6cf60b6/image.png)


### Adapter 패턴 적용후
![](https://velog.velcdn.com/images/ddh963963/post/c40a42c0-ab5b-40b2-b087-2f29ddc213d4/image.png)

- 다음 그림과 같이 각각의 target 인터페이스를 상속받은 구현체를 만들어 사용한다.

## Adapter 구현하기
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
