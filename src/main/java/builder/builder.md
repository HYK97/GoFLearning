# Builder 패턴이란 ?

- 빌더 패턴은 복잡한 객체를 생성하는 클래스와 표현하는 클래스를 분리하여 절차나 제한을 제공하는 패턴이다

## Builder의 장단점

- 장점 : Setter를 사용하지 않기 때문에 객체가 변경되는 일을 최소화할 수 있다.
절차에 따라 진행되기 때문에 매개변수가 많아져도 가독성을 높일 수 있다.
생성 과정 중 필요한 데이터들을 강제하거나 혹은 필요 없는 데이터는 설정하지 않게 할 수 있도록 유연하게 사용할 수 있다.

- 단점 : 매개변수가 많아질 경우 Setter로 할 때보다 필요한 클래스나 코드의 양이 늘어날 수 있다.

## Abstract Factory 패턴은 어떤 관계를 형성하고있을까?

### Builder 클래스다이어그램
        
![](https://velog.velcdn.com/images/ddh963963/post/c56782aa-a130-4a1c-bf3d-edece737fff6/image.png)


- 다음과 같이 Director를 이용하면 Builder의 자세한 내용을 클라이언트로부터 숨겨서 사용할 수 있다.(캡슐화)




  
## 코드의 내용
### DefaultTourBuilder

``` java
public class DefaultTourBuilder implements TourPlanBuilder {

    private TourPlan tourPlan;

    @Override
    public DefaultTourBuilder newInstance() {
        this.tourPlan = new TourPlan();
        return this;
    }

    @Override
    public TourPlanBuilder nightsAndDays(int nights, int days) {
        this.tourPlan.setNights(nights);
        this.tourPlan.setDays(days);
        return this;
    }

    @Override
    public TourPlanBuilder title(String title) {
        this.tourPlan.setTitle(title);
        return this;
    }

    @Override
    public TourPlanBuilder startDate(LocalDate startDate) {
        this.tourPlan.setStartDate(startDate);
        return this;
    }

    @Override
    public TourPlanBuilder whereToStay(String whereToStay) {
        this.tourPlan.setWhereToStay(whereToStay);
        return this;
    }

    @Override
    public TourPlanBuilder addPlan(int day, String plan) {
        if (tourPlan.getPlans() == null) {
            this.tourPlan.setPlans(new ArrayList<>());
        }

        this.tourPlan.getPlans().add(new DetailPlan(day, plan));
        return this;
    }

    @Override
    public TourPlan getPlan() {
        return tourPlan;
    }
}


```


- newInstance 메서드를 추가해서 사용하면 builder TourPlan의 필드를 반복해서 코드에 적지 않아도 돼서 가독성과 코드의 양을 줄일 수 있다.
- 리턴 타입은 다음과 같이 빌더 타입으로 넘겨줘야 한다.




## 실제로 어떻게 쓰일까 ?
    
- Spring의 UriComponents 나 Stream등에 이용할때 쓰인다.
공통적으로 빌더패턴은 new 연산자를 사용하지않고 빌더 형식에 따라서 객체의 데이터를 주입한다.



### Stream 예시(널이 아닌 값 필터링)
```java
    public static void main(String[] args) {
            Stream<Integer> stream = Stream.of(1, null, 3, 4, null);
            List<Integer> collect = stream.filter(Objects::nonNull).collect(Collectors.toList());
    }
```    
### SpringExample (Uri 생성)

```java
   public static void main(String[] args) {
         (Uri 생성) components = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host("www.naver.com")
                .path("news")
                .build().encode();
    }
```    
