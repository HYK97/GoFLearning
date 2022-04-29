# Prototype 패턴이란

### 생성할 객체들의 타입이 프로토타입인 인스턴스(원본)로부터 결정되도록 하며, 새 객체를 만들기 위해 자신을 clone 방법을 이용하는 것을 프로토타입 패턴이라고 한다


## Prototype 장단점

- 장점 :

  객체를 생성할 때 복잡한 과정을 생략하고 보다 편리하게 복제해서 사용할 수 있다.(객체 생성의 비용을 줄일 수 있다)

  초기화 코드를 불필요하게 사용할 필요가 없다.

  생성되는 객체를 캡슐화하여 정보를 숨길 수 있다.



- 단점 :

  clone() 메서드는 Object 클래스의 메서드로, 얕은 복사로 동작하기에 깊은 복제를 사용해야 할 경우 모든 객체의 clone() 메서드를 재정의 해야 한다는 단점이 있다.

  순환 참조가 있는 복잡한 객체를 복제하는 것은 어려울 수 있다.

## Prototype 구현하기

```java
 public class GithubIssue implements Cloneable { //Object 의 clone을 사용하기 위해선 Cloneable을 구현해야함
    
    ...


  @Override
  protected Object clone() throws CloneNotSupportedException { // 구현체 코드 
    //깊은 복사 클론
    GithubRepository repository = new GithubRepository();
    repository.setUser(this.repository.getUser());
    repository.setName(this.repository.getName());

    GithubIssue githubIssue = new GithubIssue(repository);
    githubIssue.setId(this.id);
    githubIssue.setTitle(this.title);

    return githubIssue;

    //얕은 복사 클론
    //return super.clone();
  }

  @Override
  public boolean equals(Object o) { //equals 재정의
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    GithubIssue that = (GithubIssue) o;
    return getId() == that.getId() && Objects.equals(getTitle(), that.getTitle()) && Objects.equals(getRepository(), that.getRepository());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getTitle(), getRepository());
  }
}
```  

- 필요에 따라서 clone() 메서드를 깊게 혹은 얕게 복사하기위해 재정의 해야한다.


## Prototype은 어떻게 사용될까?

### java

- ArrayList는 Clonable의 clone을 구현하고있어 실제로 clone을 통해서 복제가 가능하다 반면 List는 불가능하다.
```java
  public class ArrayList<E> extends AbstractList<E>
        implements List<E>, RandomAccess, Cloneable, java.io.Serializable //Cloneable 을 구현하고있음
{
 
    .
            .
            .

  /**
   * Returns a shallow copy of this {@code ArrayList} instance.  (The
   * elements themselves are not copied.)
   *
   * @return a clone of this {@code ArrayList} instance
   */
  public Object clone() { //구현 메서드
    try {
      ArrayList<?> v = (ArrayList<?>) super.clone();
      v.elementData = Arrays.copyOf(elementData, size);
      v.modCount = 0;
      return v;
    } catch (CloneNotSupportedException e) {
      // this shouldn't happen, since we are Cloneable
      throw new InternalError(e);
    }
  }
}


```

- 하지만 clone시에는 추상타입을 사용하지않고 구체적인 ArrayList 라는 구현체로 받아야 하기 때문에(List는 Cloneable 상속하지않음)

  아래와 같이 List로 받아야 하는게 낫기 떄문에 이런식으로 복사해서 사용하는게 낫다




```java

    ArrayList<Object> orignal =new ArrayList<Object>();
    ArrayList<Object> clone = (ArrayList<Object>) orignal.clone();
    
    //아래의 방법으로 Collection을 복사한다.
            
    List<Object> clone = new ArrayList<>(object);

```

