
## CH 4. 설계 품질과 트레이드오프

---
### 이번 챕터를 보기 위해선 커밋 로그들을 잘 살펴볼 것!
1. 문제되는 코드의 형태가 있다.
2. 이후에 어떻게 수정이 되었는지 확인하는 형식으로 참고하면서 보면 도움이 더 될 것이다.


### 객체지향 설계란?
>올바른 객체에게 올바른 책임을 할당하면서 **낮은 결합도**와 **높은 응집도**를 가진 구조를 창조하는 활동이다.

오늘은 위의 말에 대해서 코드를 예제로 하여 설명을 하게 된다. 잘 기억하자! 이 챕터의 가장 핵심은 위의 말이다.
첨언하면, **객체의 상태가 아니라 객체의 행동에 초점을 꼭 맞춰야 한다!**

### 객체를 분리할 때의 중심축을 어디에 맞추냐?
1. 상태를 분할의 중심축으로 삼는다.
2. 책임을 분할의 중심축으로 삼는다. 

---
### 데이터 중심으로 설계한다는 것
시스템을 분할할 때는 데이터, 책임 중 어떤 것을 택하면서 분할해야 될까?
바로 **책임**이다. 객체 지향 설계에서는 책임을 초점에 맞춰서 설계를 해야한다. 이제부터 그것에 대한 이유를 하나씩 계속 예제를 통해 알아보게 된다.


### 데이터 중심 설계의 단점

코드를 보면 알겠지만 눈으로만 봐도 이게 다 연결이 되어있어서 어느 한 곳이 잘못된 순간 모든 곳에서 변경이 같이 수행될 정도로 `결합도`가 엄청 높다!
그러면서 `응집도`도 매우 낮다는 것을 알 수가 있다.

> 객체를 설계하기 위한 가장 기본적인 아이디어는 변경의 정도에 따라 구현과 인터페이스를 분리하고 외부에서는 인터페이스만 의존하도록 관계를 조절하는 것이다.

1차적인 코드를 작성했을때의 문제점을 보고 위와 같은 말을 책에서 해준다. 그러면서 위의 말은 **캡슐화**랑 거의 똑같은 말이다.

>캡슐화는 외부에서 알 필요가 없는 부분을 감춤으로써 대상을 단순화하는 추상화의 한 종류이다.
> 캡슐화는 즉, 변경 가능성이 높은 부분을 객체 내부로 숨기는 추상화 기법이다!

그래서 어떤 변경 가능성이 생길 수 있는 부분이라도 캡슐화를 해줘야 한다!


### 응집도, 결합도

- 응집도 : 모듈에 포함된 내부 요소들이 연관돼 있는 정도를 나타낸다.
- 결합도 : 의존성의 정도를 나타내며 다른 모듈에 대해 얼마나 많은 지식을 갖고 있는지를 나타내는 척도

그런데 이 말의 의미들이 되게 애매하고 모호하다. 그래서 우리는 모두 이 2 가지가 객체 설계와 관련이 있다는 사실을 이해하고 간다고 생각하고 시작하는 것이 좋다.

각 요소의 응집도가 높고 서로 느슨하게 결합돼 있다면 그 애플리케이션은 좋은 설계를 가진다고 했다.
이렇게 만들어야 되는 이유는 바로 설계를 변경하기 쉬워지기 때문이다.

그래서 책에서는 이렇게도 바라본다. **변경이 발생할 때 모듈 내부에서 발생하는 변경의 정도로 응집도를 측정할 수 있다.**
그러면? 결합도는 **한 모듈이 변경되기 위해서 다른 모듈의 변경을 요구하는 정도**로 측정할 수 있다.

이 말을 간단하게 요약해보면 한 모듈에서 변경되는 내용이 모듈 내에서만 일어나게 하면 -> **응집도가 높고 결합도가 낮은** 좋은 객체 설계를 가진다는 것을 알 수 있다.

### 결합도가 높아도 되는 경우?

결합도가 높으면 설계가 안 좋은 측면이라고 했는데 높아도 될 때가 있긴 하다.

ex) 자바에서의 String, ArrayList와 같은 객체들은 변경 가능성이 낮다.

**변경될 확률이 매우 낮은 경우에서는 결합도에 대해 고민할 필요가 별로 없다. **


### 코드가 수정이 어려워진다면?(하나의 수정으로 인해 다른 것에 영향이 많이 간다면?)
그러면 그 코드는 응집도가 너무 낮고 결합도가 아주 높다는 것이다.

---

### 데이터 중심 설계의 대표적인 문제점

- 캡슐화 위반
- 높은 결합도
- 낮은 응집도


### 캡슐화를 위반한다는 것

예를 들면 이런 코드다.

```java
public class Movie{
    private Money fee;
    
    public Money getFee(){
        return fee;
    }
    
    public void setFee(Money fee){
        this.fee=fee;
    }
}

```

이 코드를 보면 Movie라는 객체에서 거의 Money에 있는 fee라는 인스턴스 필드가 존재한다는 걸 대놓고 보여주고 있다.
getter,setter를 통해서 내부로서의 접근을 막았다고 해도 막기만 했지 사실상 밖에서 다 보여주고 다니는 모양이다. 캡슐화가 제대로 안 된 것이다.

이렇게 getter,setter에만 너무 집중해서 설계를 하는 방식을 **추측에 의한 전략**이라고 부르기로 했다.

### 높은 결합도

이 코드를 보자.

```java
public class ReservationAgency {

    public Reservation reservation(Screening screening, Customer customer, int audienceCount){
        Movie movie = screening.getMovie();

        boolean discountable=false;
        for(DiscountCondition condition : movie.getDiscountConditionList()){
            ...
            
        Money fee;
        if(discountable){
            Money discountAmount = Money.ZERO;
            switch (movie.getMovieType()){
                case AMOUNT_DISCOUNT:
                    ...
}
```

이런거 처럼 내부 구현에대한 것을 접근자와 수정자를 통해서 다 끌어다가 밖에서 구현을 대놓고 하고 있다. 그러니 이건 캡슐화를 위반한 것이다.
이것과 연관된 코드에서 수정이 하나라도 일어나면 여기에서 대규모로 코드를 수정해줘야한다. 

#### 결합도 측면에서 데이터 중심 설계가 가지는 단점
**하나의 제어 객체 안에서 다수의 데이터 객체에 강하게 결합된다는 것이다.**


### 낮은 응집도

이 부분은 위의 ReservationAgency 코드에서 보면 알 수 있다. 아까 말했던 것처럼 아래와 같은 수정사항이 생겼다고 해보자.
- 할인 정책이 추가될 경우
- 할인 정책별로 할인 요금을 계산하는 방법이 변경될 경우
- 할인 조건이 추가되는 경우
- 할인 조건별로 할인 여부를 판단하는 방법이 변경될 경우
- 예매 요금을 계산하는 방법이 변경될 경우


이렇게 되면 하나 이상의 클래스를 수정해야 하는 일이 생기게 된다. 이런 것처럼 **하나 이상의 클래스를 수정해야 하는 것은 설계의 응집도가 낮다는 증거이다.**

### 단일 책임 원칙(SRP: Single Responsibility Principle)
> 클래스는 단 한가지의 변경 이유만 가져야 한다는 것 -> 응집도를 높일 수 있는 설계 원칙

---

### 캡슐화를 지키는 것
캡슐화가 결국 설계의 제 1원리라는 것을 앞선 예제를 통해 알 수 있었다. **객체는 스스로의 상태**를 책임져야 하고 외부에서는 인터페이스에 정의된 메서드를 통해서만 상태에 접근할 수 있어야 하는 것이다.

### Rectangle예제

코드 안에서 패키지를 나누어 잘못된 설계, 올바른 설계를 나눠놨다. 요약해보면 이런 상황이다.

- 잘못된 설계에서는 외부에서 접근자와 수정자를 이용해서 구현을 하고 있다.
- 올바른 설계에서는 잘못된 점을 바로 잡기 위해서 본인의 상태와 관련된 작업을 내부로 캡슐화시켰다.

책에서 잘못된 설계의 단점을 이렇게 말하고 있다.
1. '코드 중복'이 발생할 확률이 높아진다.
2. '변경에 취약'하다는 점이다.

당연한 말이다. 1.의 경우에는 다른 곳에서도 사각형의 너비와 높이를 증가시키고 싶으면 또 똑같은 코드가 작성이 되야 한다.
2.의 경우에는 Rectangle이라는 클래스 안의 상태를 변경하게 되면 밖에서도 영향을 받을 수 밖에 없는 상황인 것이다.
이렇게 응집도가 낮아지고 결합도가 높아진 것을 볼 수 있었다.

### 핵심은 스스로 자신의 데이터를 책임져야 하는 객체가 되야 한다.
그래서 스스로의 상태에 대해서는 객체 스스로가 처리를 할 수 있어야 한다. 

사실은 영화 예매와 같은 예제도 이런 부분들을 적용하면 응집도가 높아지고 결합도가 낮아질 수 있다.

- DiscountCondition: 조건에 관련된 것은 스스로 
- DiscountPolicy : 할인에 관련된 정책은 스스로 처리
- Movie : 영화의 할인 가격에 관련된 정보는 스스로
- Screening : 영화의 가격에 대한 정보는 안에서 처리
- ReservationAgency : 예약에 관해서는 스스로

그래서 코드를 보면 알 수 있겠지만 각자가 처리해야 하는 파트에서 캡슐화를 진행하여 다 내부로 감추는 작업을 진행하였다.
이렇게 해서 응집도를 높이고 결합도를 낮추는 작업을 실시하였다. 

### 그치만! 아직 단점이 남아있다.

```java
 public Money calculateFee(int audienceCount){
        switch (movie.getMovieType()){
            case AMOUNT_DISCOUNT:
                if(movie.isDiscountable(whenScreened,sequence)){
                    return movie.calculateAmountDiscountFee().times(audienceCount);
                }
                break;
            case PERCENT_DISCOUNT:
                if(movie.isDiscountable(whenScreened,sequence)){
                    return movie.calculatePercentDiscountFee().times(audienceCount);
                }
                break;
            case NONE_DISCOUNT:
                return movie.calculateNoDiscountFee().times(audienceCount);
        }
        return movie.calculateNoDiscountFee().times(audienceCount);
    }

```

위 코드는 Screening의 일부인 calculationFee인데 만약 할인 정책이 늘어나면 어떻게 될까? 그러면 DiscountPolicy에 관련한 내용인데 아직 
영향력이 Screening에도 뻗어나가서 수정해야 하는 일이 일어난다. 

---

### 데이터 중심 설계는 객체의 행동보다는 상태에 초점을 맞추게 된다.

데이터 중심 설계가 취약한 이유는 크게 2 가지이다.
1. 데이터 중심의 설계는 본질적으로 너무 이른 시기에 데이터에 관해 결정하도록 강요한다.
2. 데이터 중심의 설계에서는 협력이라는 문맥을 고려하지 않고 객체를 고립시킨 채 오퍼레이션을 결정한다.

결론적으로 많은 말이 있지만 데이터 중심의 설계는 너무 이른 시기에 데이터에 관해 고민해버리기 때문에 캡슐화를 실패할 수 밖에 없게 된다. 

이것이 당연한 이유가 데이터에 초점을 맞추면 **접근자, 수정자**가 너무 많아지고 다른 곳에서는 이를 활용해서 코드를 구성해 나가야 한다. 

결국 데이터 중심적으로 설계를 해버리면 객체의 외부가 아닌 내부로 초점이 향할 수밖에 없다. 그래서 요구사항이 변경되게 된다면 다른 곳에서 다 영향을 받아버릴 수 밖에 없다는 것이다!
이것은 요약하면 **데이터 중심 설계는 응집도가 낮아지고 결합도가 높아지는 설계 방법이었다.**

그래서 우리는 항상 책임 할당 원칙을 지켜나가야 하는데 이는 다음챕터에서 알아보도록 하자!