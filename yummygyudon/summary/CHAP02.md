# Chapter 2. 객체 지향 프로그래밍
<br/>

" **객체지향** "이라는 것은 말 그대로 객체 활용을 지향하는 것이로

보통 Class(클래스) 라는 이름을 가진 객체를 사용하며
객체 지향 프로그램을 작성할 떼,
보통 어떤 클래스가 필요한지 고민하고 **결정한 후, 해당 클래스에는 어떤 속성과 메서드가 필요한지 고민**하지만

이는 객체 지향의 **본질과는 거리가 멀다**.

> ### [ 객체 지향 프로그램을 위한 집중 ]
>1. 어떤 클래스가 필요한지를 고민하지 전에 " **<u>어떤 객체들이 필요한지</u>** " 고민하라.<br/>
>: 클래스(Class) 란 공통적인 상태와 행동을 공유하는 객체들을 추상화한 것이다.<br/>
그에 따라 클래스의 윤관을 잡기 위해서는<br/>
**어떤 객체들이 어떤 상태와 행동을 가지는지를 먼저 결정**해야한다
>
><br/>
>
>2. 객체를 독립적인 존재가 아닌 " **기능을 구현하기 위해 <u>협력하는 공동체의 일원</u>** "으로 생각하라.<br/>
>: 객체는 홀로 존재하는 것이 아닌<br/>
다른 객체에게 도움을 주거나 반대로 의존하며 살아가는 **협력적인 존재**이다.<br/>
그렇기에 객체를 협력하는 공통체의 일원으로 바라보는 것이<br/>
유현하고 확장 가능한 설계로 향하는 방법이 된다.

객체 지향적으로 생각하기 위해선<br/>
객체를 고립된 존재가 아닌 " 협력에 참여하는 **협력자** "로 바라보는 것이 좋다.

<br/>

## 도메인 구조를 따르는 프로그램 구조

> **도메인(domain)** : 문제를 해결하기 위해 사용자가 프로그램을 사용하는 분야

요구사항을 분석하는 초기 단계부터 프로그램을 구현하는 마지막 단계까지<br/>
“객체”라는 동일한 추상황 기법을 사용할 수 있다.

그에 따라 요구사항과 프로그램을 객체라는 동일한 관점에서 바라볼 수 있기 때문에<br/>
도메인을 구성하는 개념들이 자연스레 객체와 클래스로 연결될 수 있다.

( 클래스 사이의 관계도 <u>최대한 도메인 개념 사이에 맺어진 관계와 유사하게</u> 만들어 프로그램의 구조를 이해하고 예상하기 쉽게 만들어야 한다. )

<br/>

### 자율적인 객체
우선 객체에 대해서 짚고 넘어가야 할 중요한 부분이 있다.

1. 객체는 **상태(state)** 와 **행동(behavior)** 를 가진 **복합적**인 존재이다.
2. 객체가 <u>스스로 판단하고 행동</u>하는 **자율적**인 존재

즉, 객체는 <u>데이터와 기능을 묶어 놓은 **집합체**</u>로<br/>
<u>문제 영역의 아이디어를 표현</u>하기 위한 **단위**라고 생각해야 한다.

이것을 바로 " **캡슐화(Encapsulation)** "라고 한다.

더 나아가 객체를 **자율적인 존재**로 만들고<br/>
더 완성도 높은 **캡슐화**를 완성시키기 위해서<br/>
외부에서의 접근까지 통제할 수 있는 <u>접근제어(access control) 매커니즘</u>도 제공되는 것이다.<br/>
(_접근 제어를 위해 사용되는 제어자 `private` / `protected` / `public` : 접근 수정자_ )

당연하겠지만<br/>
자율적인 존재로 만들기 위해서는 외부의 간섭을 최소화해야 하기 때문에<br/>
접근 제어를 통해<br/>
외부에서는 객체가 <u>어떤 상태인지, 어떤 행동을 하는지</u> 알게하거나<br/>
더불어 <u>행동에 개입하는 것</u>을 **막아야 한다**.

이렇게 접근 제어와 캡슐화를 통해 객체를 생성했을 때,<br/>
<u>외부에서 접근 가능한 최소한의 부분</u>을 **" 공용 인터페이스(public interface) "** 라 하고<br/>
<u>내부에서만 접근 가능한 부분</u>을 **" 구현(implementation) "** 이라 부른다.

공용 인터페이스와 구현을 분리한다는 원칙을<br/> " **인터페이스와 구현의 분리**(_Separation of Interface and Implementation_) 원칙 "이라 칭한다.

<br/>

### 자유로운 활용
프로그램을 개발하는 프로그래머의 역할을<br/>
"**클래스 작성자**(class creator)" 와 "**클라이언트 프로그래머**(client programmer" 로 구분하는 것도 유용하다.

- **클래스 작성자** : 새로운 데이터 타입을 추가하는 역할
- **클라이언트 프로그래머** : 추가된 데이터 타입을 사용하는 역할

클라이언트 프로그래머의 입장에서 생각해보자면<br/>
우선 필요한 클래스들을 활용해서 애플리케이션을 빠르고 안정적으로 구축하는 것이 목표일 것이다.

이를 위해선 각 클래스 <u>내부 구현까지 신경쓰면서 작업이 지체되지 않고</u><br/>
**편하게 활용할 수 있어야 할 것**이다.

이 때, 클라이언트 프로그래머가 <u>클래스의 내부에 마음대로 접근할 수 없도록 방지</u>하는 것을<br/>
"**구현 은닉**(implementation hiding)"이라 하며<br/>
이를 통해 클라이언트 프로그래머는 편하게 걱정없이 명시적인 기능만 활용하며 구축할 수 있고<br/>
동시에 클래스 작성자 또한 <br/>
<u>클라이언트 프로그래머에 대한 영향을 걱정하지 않고</u>도 **내부 구현을 마음대로 변경**할 수 있게 된다.

<br/>

---

지금부터 구현 코드를 살펴보며 좋은 설계를 알아보자.

## 구현

도메인 개념들의 구조를 반영하는 클래스 구조를 만들었다면<br/>
채택된 프로그래밍 언어로 해당 구조를 구현하는 것이 수순이다.

이에 따라 해당 단계에서 가장 중요한 것은<br/>
위에서 살펴봤다시피
<u>클래스와 내외부의 경계를 구분 짓는 **캡슐화**와 **구현 분리**</u>이다.

<u>경계가 명확</u>할수록 **객체의 자율성을 보장**하고 **구현의 자유**를 제공할 것이다.<br/>
다시 한 번 강조하지만<br/>
훌륭한 클래스 설계를 위한 핵심은 **어떤 부분을 외부에 공개하고 어떤 부분을 감출지 결정**하는 것이고<br/>
멤버들의 접근제어자를 설정함으로서 달성할 수 있다.

우선 구현에 활용할 하나의 일반 클래스를 만들어보자.

아래 예시 클래스에서도 확일할 수 있듯이<br/>
`private Movie movie`와 같이 외부에서는 객체의 속성에 접근할 수 없도록 하고<br/>
해당 속성의 제어는 적절한 `public`메서드를 통해서만 가능하도록 이루어져 있다.
```java
public class Screening {
  private Movie movie;
  private int sequence;
  private LocalDateTime whenScreened;

  public Screening(Movie movie, int sequence, LocalDateTime whenScreened) {
    this.movie = movie;
    this.sequence = sequence;
    this.whenScreened = whenScreened;
  }

  public LocalDateTime getWhenScreened() {
      return whenScreened;
  }
  
  public boolean isSequence(int sequence){
      return this.sequence == sequence;
  }
  
  public Money getMovieFee(){
      return this.movie.getFee();
  }
}
```

### 협력 공동체
위 "**상영**"에 대한 객체인 `Screening` 클래스를 마저 살펴보자.

우선 해당 상영에 대해서 어떠한 고객이 예매를 하는 경우에 대한 처리를 추가해보자.<br/>
어느 자리에 예매하는 것인지까지 체크하면 좋겠지만 넘어가도록 하자.

하지만 사실 객체 지향 설계를 하다보면 자주 드는 한 가지 고민이 있다.<br/>
바로 <u>어느 정도까지 객체로 표현하는 것이 좋은지</u> 고민하는 것이다.

하지만 객체 지향의 장점 자체가 <br/>
**객체를 이용해 도메인의 의미를 풍부하고 다양하게 표현할 수 있다**는 것이기 때문에

비록 <u>하나의 인스턴스 변수만 포함하더라도</u> 개념을 명시적으로 표현하는 것이<br/>
명확하고 유연한 객체 지향 설계의 첫 걸음이라고 볼 수 있다.

즉, 너무 자세하게 나누는 것이 아닌가 걱정하는 것보다 **최대한 명시적으로** 객체 설계하는 것이 좋다.

```java

import domain.Customer;

public class Screening {
  /**
   * 새로운 예매 정보 객체(Reservation)를 생성한다.
   * - 예매자 정보
   * - 해당 상영 정보
   * - 예매 금액 (calculateFee 메서드 사용)
   * - 인원 수
   *
   * @param customer 예매자 정보
   * @param audienceCount 예매 인원 수
   */
  public Resevation reserve(Customer customer, int audienceCount) {
    return new Reservation(customer, this, calculateFee(audienceCount), audienceCount);
  }

  /**
   * times 함수를 통해 calculateMovieFee 메서드 반환값에 인원 수 인 audienceCount 를 곱해준다.
   *
   * @param audienceCount 예매 인원 수
   */
  private Money calculateFee(int audienceCount) {
    return movie.calculateMovieFee(this).times(audienceCount);
  }
}

public class Money {
  public static final Money ZERO = Money.won(0);
  private final BigDecimal amount;

  public static Money wons(long amount) {
    return new Money(BigDecimal.valueOf(amount));
  }

  // 오버로딩
  public static Money wons(double amount) {
    return new Money(BigDecimal.valueOf(amount));
  }

  Money(BigDecimal amount) {
    this.amount = amount;
  }

  public Money plus(Money amount) {
    return new Money(this.amount.add(amount.amount));
  }

  public Money minus(Money amount) {
    return new Money(this.amount.subtract(amount.amount));
  }

  public Money times(double percent) {
    return new Money(this.amount.multiply(BigDecimal.valueOf(percent)));
  }

  public boolean isLessThan(Money other) {
    return amount.compareTo(other.amount) < 0;
  }

  public boolean isGreaterThanOrEqual(Money other) {
    return amount.compareTo(other.amount) >= 0;
  }
}

public class Reservation {
  private Customer customer;
  private Screening screening;
  private Money fee;
  private int audienceCount;
  
  public Reservation(Customer customer, Screening screening, Money fee, int audienceCount){
      this.customer = customer;
      this.screening = screening;
      this.fee = fee;
      this.audienceCount = audienceCount;
  }
}
```

위처럼 각 인스턴스들은 서로의 메서드 호출을 통해서 상호작용하며 기능을 수행하는데<br/>
이를 **협력(Collaboration)** 이라 칭한다.

> ### 협력(Collaboration)
> 객체의 내부 상태는 외부에서 접근하지 못하도록 감추고<br/>
> 외부에 공개하는 퍼블릭 인터페이스를 통해 접근할 수 있도록 하는 것이 기본 전제이다.
> 
> 이런 접근을 통한 상호작용은<br/>
> 다른 객체 인터페이스에 특정 행동을 수행하도록 요청하는 것과<br/>
> 요청을 받은 객체가 자율적인 방법에 따라 요청을 처리한 후 응답하는 것으로 이루어져 있다.
> 
> - **요청**(**Request**) : **메시지 전송**(_send a message_)
> - **응답**(**Response**) : **메시지 수신**(_receive a message_)
>   - 응답 객체의 **메시지 처리 방법** : **메서드**(_method_)
> 
> <u>유연하고 확장 및 재사용이 가능한 객체지향 패러다임의 설계를</u> 위해서는<br/>
> **메시지와 메서드를 구분하는 것**이 매우 중요하다.
> 
> 실제 코드를 보면 다른 인스턴스의 메서드를 호출하는 모양새이지만<br/>
> 사실 메서드를 **호출**한다라기 보다는 **해당 메서드로 메시지를 전송**한다가 <br/>더 맞는 표현이라 볼 수 있다.

<br/>

---

<br/>

이번엔 할인 정책에 따른 다양한 할인 적용을 생각해보자.
우선 Movie 라는 클래스를 보면
```java
public class Movie {
  private String title;
  private Duration runningTime;
  private Money fee;
  private DiscountPolicy discountPolicy;

  public Movie(String title, Duration duration, Money fee, DiscountPolicy discountPolicy) {
    this.title = title;
    this.runningTime = duration;
    this.fee = fee;
    this.discountPolicy = discountPolicy;
  }

  public Money getFee() {
    return fee;
  }

  public Money calculateMovieFee(Screening screening){
      return fee.minus(discountPolicy.calculateDiscountAmount(screening));
  }
}
```
이렇게 영화에 적용되어 있는 `DiscountPolicy`에 따라 할인이 적용된 금액을 반환하는
`calculateMovieFee` 메서드가 있다.

하지만 여기서 발견할 수 있는 문제는<br/>
할인정책에는 정액 할인과 비율 할인이라는 2가지 이상의 정책이 존재하기 때문에<br/>
경우에 따라 적절한 할인이 적용되어야 하는데<br/>
위와 같이 단순 DiscountPolicy 라는 포괄적인 개념으로 적용되어 있는 것을 볼 수 있다.

이런 경우 필요한 개념이자 객체 지향의 중요 개념이 바로<br/>
**추상화**(**abstraction**)를 기반한 **상속**(**inheritance**)과 **다형성**이다.

### Abstract 활용

```java
public abstract class DiscountPolicy {
  private List<DiscountPolicy> conditions;

  public DiscountPolicy(DiscountCondition... conditions) {
    this.conditions = Arrays.asList(conditions);
  }

  public Money calculateDiscountAmount(Screening screening) {
    for (DiscountCondition condition : conditions) {
      if (condition.isSatisfiedBy(screening)) {
        return getDiscountAmount(screening);
      }
    }
    return Money.ZERO;
  }

  abstract protected Money getDiscountAmount(Screening screening);
}

/**
 * 할인정책(DiscountPolicy)의 할인 조건 객체들을 위한 인터페이스
 * - isSatisfiedBy 메서드 표준화
 */
public interface DiscountCondition {
  boolean isSatisfiedBy(Screening screening);
}

// 순번 조건
public class SequenceCondition implements DiscountCondition {
  private int sequence;

  public SequenceCondition(int sequence) {
    this.sequence = sequence;
  }

  @Override
  public boolean isSatisfiedBy(Screening screening) {
    return screening.isSequence(sequence);
  }
}

// 기간 조건
public class PeriodCondition implements DiscountCondition {
  private DayOfWeek dayOfWeek;
  private LocalTime startTime;
  private LocalTime endTime;

  public PeriodCondition(DayOfWeek dayOfWeek, LocalTime startTime, LocalTime endTime) {
    this.dayOfWeek = dayOfWeek;
    this.startTime = startTime;
    this.endTime = endTime;
  }

  @Override
  public boolean isSatisfiedBy(Screening screening) {
    return screening.getStartTime().getDayOfWeek().equals(dayOfWeek)
            && startTime.compareTo(screening.getStartTime().toLocalTime()) <= 0 
            && endTime.compareTo(screening.getStartTime().toLocalTime()) >= 0;
  }
}
```
이렇게 추상 클래스로 `DiscountPolicy` 클래스로 만들어 보았는데<br/>
실행 구조는 만약 해당 할인 정책 클래스에 속해있는 여러 할인 조건들(`conditions`) 중<br/>
매개변수로 들어온 상영 건에 적용되는 조건일 경우(`isSatisfiedBy`) 금액을 계산하여 반환하고<br/>
만약 적용된 정책이 없을 경우, 할인금액은 0(`Money.ZERO`)으로 책정하여 반환한다.

아래의 추상 메서드는 본 클래스를 상속받은 후,<br/>
다양한 정책들 각자에게 맞는 할인 금액 계산법을 구현하면 <br/>
DiscountPolicy와 협력하는 다른 인스턴스에서는 자유롭게 할인 정책을 적용할 수 있을 것이다.<br/>

이처럼 경우에 따라 다른 금액 계산에 대한 작업을 자식 클래스에게 위임하는 디자인 패턴을<br/>
**템플릿 메서드**(**Template Method**) 디자인 패턴이라고 칭한다.

> ### Template Method Pattern
> **부모 클래스**에서 <u>기본적인 알고리즘의 흐름을 구현</u>하고 <br/>
> **중간에 필요한 처리**는 <u>**자식 클래스**에게 위임</u>하는 디자인 패턴
> ```java 
> /**
>  * 부모 클래스 DiscountPolicy를 상속받은
>  * 자식 클래스 AmountDiscountPolicy(금액할인) / PercentDiscountPolicy(비율할인)
>  * 
>  * getDiscountAmount 추상 메서드 구현
>  */
> public class AmountDiscountPolicy extends DiscountPolicy {
>   private Money discountAmount;
> 
>   public AmountDiscountPolicy(Moeny discountAmount, DiscountCondition ... conditions) {
>     super(conditions);
>     this.discountAmount = discountAmount;
>   }
> 
>   @Override
>   protected Money getDiscountAmount(Screening screening) {
>     return discountAmount;
>   }
> }
> 
> public class PercentDiscountPolicy extends DiscountPolicy {
>   private double percent;
> 
>   public PercentDiscountPolicy(double percent, DiscountCondition ... conditions) {
>     super(conditions);
>     this.percent = percent;
>   }
> 
>   @Override
>   protected Money getDiscountAmount(Screening screening) {
>     return screening.getMovieFee().times(percent);
>   }
> }
> ``` 


위와 같은 디자인 패턴을 적용하면
Movie 클래스 내의 DiscountPolicy 필드로
AmountDiscountPolicy / PercentDiscountPolicy 둘 중 어떠한 클래스의 인스턴스가 와도
문제가 생기지 않는다.

`AmountDiscountPolicy` 와 `PercentDiscountPolicy` 는<br/>
Movie에서 의존하고 있는 **추상 클래스 DiscountPolicy를 상속받아 구현**되었기 때문에<br/>
**실행 시점**에 `AmountDiscountPolicy` 나 `PercentDiscountPolicy` 인스턴스 둘 중 <br/>
가지고 있는 정책 클래스에 의존하게 되는 것이다.

물론 코드에서는 이 실행시점에 연결되는 의존성을 직접적으로 표현되지 않는다.
이처럼 Movie 클래스와 같이 <u>코드의 의존성과 실행 시점의 의존성이 서로 다를 수 있다.</u>

이것이 바로 객체지향 설계가 가지는 특징 중 하나인 것이다.

유연하고 쉽게 재사용할 수 있으며 확장이 용이하다는 특징을 가지고 있지만<br/>
그에 따르는 분명한 단점도 존재한다.

1. 의존성이 다양해질수록 코드를 이해하기 어렵다.
2. 설계가 유연해질수록 디버깅하기 어렵다.

이런 확연한 장단점이 있기에<br/>
객체 지향 설계에는 무조건 정답이 있는 것이 아니고<br/>
**상황에 따른 트레이드 오프(Trade-Off) 계산**에 따라 현명한 선택을 하는 것이 유일한 방법이다.

<br/>

---

### 차이에 의한 프로그래밍

객체 지향 설계를 살펴보면서
정말 자주 나오는 얘기 중 하나가 **재사용성**일 것이다.

예를 들어,
클래스를 하나 추가하고 싶은데
이미 존재하는 다른 클래스의 내용과 90%는 유사하고
10%만 바꿔야할 경우,

같은 내용을 똑같이 작성해야하는 수고로움과 더불어
쓸데없이 가독성과 프로그램 사이즈를 낭비하는 상황이 발생한다.

이런 문제를 해결에 주는 것이 바로 **상속**이다.

상속을 통해서 클래스 사이에 관계를 설정하는 것만으로도 
기존 클래스가 가지고 있는 모든 속성과 행동을 새로운 클래스에 포함시킬 수 있다.

그렇기 때문에 상속은 기존 클래스를 기반으로 <br/>
새로운 클래스를 쉽고 빠르게 추가할 수 있는 방법이다.

또한 코드의 재사용을 넘어<br/> 
내부에 포함되어있던 인터페이스까지도 모두 강력하게 상속시켜지기 때문에<br/>
자식 클래스는 **부모 클래스가 수신할 수 있는 모든 메시지를 수신할 수 있다**는 특징이 있다.

덕분에 상황에 따라,외부에서 유동적으로 변할 수 있는 필요 인스턴스에 대해<br/>
자식 클래스를 부모 클래스와 동일한 타입으로 간주할 수 있는 **다형성**(**polymorphism**)이라는 특징까지 활용하여 <br/>
**의존성에 대한 대처**가 가능해져<br/>
타 객체들과의 유기적인 협력관계를 맺기 수월해진다.<br/>
(_이처럼 **다형성**을 활용하여 <u>자식 클래스가 부모클래스를 대신하는 것</u>을 **업캐스팅**[**upcasting**]이라 부른다._)

<br/>

---
### 다형성

다형성이란 **동일한 메시지를 수신했을 때,<br/>
객체의 타입에 따라 다르게 응답할 수 있는 능력**이라 할 수 있는데

이에 따라 다형적인 협력에 참여하는 객체들은<br/>
모두 같은 메시지를 이해할 수 있어야 하며<br/>
이 말은 즉슨, **인터페이스가 동일해야 한다는 것**이다.

앞서 살펴봤던 케이스는 **상속**을 사용한 케이스인데<br/>
상속을 하게 되면 <u>동일한 인터페이스를 물려받기 때문에</u><br/>
서로 다른 자식 클래스일지라도 **상속을 통해 인터페이스가 통일되어** 협력할 수 있었던 것이다.

이처럼 다형성을 실현하는 다양한 방법들에게<br/>
공통점이 있다면 메시지에 **응답하기 위해 실행될 메서드**는<br/>
컴파일 시점이 아닌 **실행 시점**에 결정한다는 것이다.<br/>

> - *전통적인 함수 호출과 같이 **컴파일 시점에 결정되는 것***<br/>
> : ”**초기** 바인딩(**Early** binding)” / “**정적** 바인딩(**Static** binding)”
>
> 
> - *메시지와 메서드를 **실행 시점**에 바인딩 하는 것*<br/>
> : "**지연** 바인딩(**Lazy** binding)" / "**동적** 바인딩(**Dynamic** Binding)"

<br/>

---
### 방법 선택 트레이드 오프

다형성과 상속을 활용하여 유연한 협력 관계를 설계하는 방법을 알아봤는데<br/>
사실 상속의 경우,<br/>
부모 클래스의 구현에 대해서는 자식 클래스는 바꿀 수 있는 권한이 없다.

만약 부모 클래스에서 살짝만 바꾸고 싶거나 한 가지만 수정하고 싶어도<br/>
무조건 그 코드에 따라야 하기 때문에<br/>
위 목표를 이루고 싶으면 똑같은 코드가 많더라도 그대로 옮겨적어 수정하거나 포기하는 수밖에 없다.

즉, 기본 기능의 유동성에서는 생각보다 폐쇄적인 것이다.

더 유연하고 개방적인 다형성을 이루고 싶다면<br/>
인터페이스를 조금 더 원시적인 위치에서 감싸고 객체 상속 구현에 대한 자유도를 조금 높이는 것이 좋다.

하지만 앞서 설명했다시피<br/>
유연함과 재사용성 증진과 대비되는 단점 또한 명확하기 때문에<br/>
트레이드 오프를 잘 고민해야한다.

> ### "구현" 상속 vs "인터페이스" 상속
> 1. **구현** 상속 : **Implementation** Inheritance<br/>
>  = <u>순수하게 **코드**를 **재사용**하기 위해</u> 상속을 사용하는 것<br/>
>  ( _**서브 클래싱** [ **SubClassing** ] 이라고도 불림_ )
> 
> 
> 2. **인터페이스** 상속 : **Interface** Inheritance<br/>
>  = <u>**다형적인 협력**을 위해</u> 부모 클래스와 자식 클래스가 **인터페이스를 공유**할 수 있도록 상속을 이용하는 것<br/>
>  ( _**서브 타이핑** [ **SubTyping** ] 이라고도 불림_ )
> 
> ✓ 상속은 구현 상속이 아닌 **인터페이스 상속**을 위해 사용해야 한다.<br/>
> ( *인터페이스를 재사용할 목적이 아니라 구현을 재사용할 목적으로 상속을 사용하면<br/>
**변경에 취약한 코드**가 될 가능성이 높아진다.* )

<br/>

---
### 합성

→ **인터페이스**에 정의된 메시지를 통해서만 코드를 재사용하는 방법

이 합성은 상속이 가지는 문제점을 해결해준다.

( 그렇다고 상속을 절대 사용하지 말라는 것이 아닌 앞서 말했듯이 상충관계를 철저히 계산하여 선택하는 것이 바람직하다. )

1. 인터페이스에 정의된 메시지를 통해서만 재사용이 가능 → 효과적인 **구현(implement) 캡슐화**
2. 의존하는 인스턴스 교체가 비교적 쉬움 → **유연한 설계**
3. 메시지를 통한 느슨한 결합 (***상속 : 클래스를 통한 강한 결합 관계*** ) → **코드 재사용성** 증진

상속보다 인스턴스 변수로 관계를 연결한 설계가 더 유연하다.
( 코드 재사용을 위해서 강력한 연결을 상속보다는 인터페이스+추상클래스 + 인스턴스 변수로 관계 연결의 방법이 합리적이다. )