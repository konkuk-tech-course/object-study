# Chapter 12. 다형성
> ⁜ **다형성**(**Polymorphism**) : <u>**런타임**</u>에 메세지를 처리하기에 적합한 메서드를 "**동적**으로 탐색"하는 과정을 통해 구현됨.
> <br/><br/>
> ⁜ **상속** : <u>**클래스 계층**</u>의 형태의 "적합한 메세지 처리 메서드를 찾기 위한 일종의 탐색 경로"를 구현하기 위한 방법

- "**상속**" 목적 : <u>**타입 계층**</u> 구조화
  - **클라이언트 관점**에서 <u>인스턴스들을 **동일하게 행동하는 그룹**으로 묶어</u> 사용 및 관리 가능
  - _단순 코드 재사용을 위해 사용하기엔 부적절_<br/>(<span style="color:grey">과거 가장 일반적인 다형성 구현 방법이었지만 최근엔 다양한 방법이 등장</span>)

## 다형성
> 하나의 **추상 인터페이스**에 대해 코드를 작성 <br/>→ 추상 인터페이스에 대해 <u>서로 다른 구현을 **연결**</u>할 수 있는 능력
><br/><br/>
> : 여러 타입을 대상으로 동작할 수 있는 코드를 작성할 수 있는 방법

### 1️⃣ 유니버설 다형성 ( _Universal Polymorphism_ )
#### ⁜ " _매개변수_ ( _Parametric_ ) " 다형성
- <u>클래스의 **인스턴스 변수**</u> / <u>메서드의 **매개변수**</u> 타입을 **임의의 타입**으로 선언 & **사용 시점**에 <u>구체 타입 지정</u>하는 방식
  - _ex. `List<T>`_
  - "**제네릭 프로그래밍**"과 연관 있음.
  - 해당 인터페이스는 <u>다양한 타입의 요소</u>를 다루기 위해 **동일한 오퍼레이션** 사용 가능<br/><br/>

<br/>

#### ⁜ " _포함_ ( _Inclusion_ ) " 다형성
- `서브타입(Subtype)`다형성 이라 불리기도 함
- 가장 일반적으로 널리 알려진 다형성
- <u>메시지가 동일하더라도</u> "**수신한 객체 타입**"에 따라 <u>실제 수행되는 행동이 달라지는</u> 방식
  - 가장 일반적인 구현 방법 : **상속**
  - 부모 클래스의 메서드 **오버라이딩** → 클라이언트는 **부모 클래스만** 참조
- **전제 조건** : 자식 클래스가 <u>부모 클래스의 서브 타입</u>
  - (_코드 재사용이 아닌_) 다형성을 위한 **<u>서브타입 계층</u>** 구축
  - 파이프 라인 구조처럼 <u>상속 계층 내</u>에서 **메시지 처리에 적절한 메서드를 탐색**

<br/>

### 2️⃣ 애드 혹 다형성 ( _Ad Hoc Polymorphism_ )

#### ⁜ " _오버로딩_ ( _Overloading_ ) " 다형성
- 하나의 클래스 안에 동일한 이름의 메서드가 존재하는 경우
  - 유사 작업 수행에 대해 메서드의 이름이 통일되어있기 때문에, 기억/인지해야할 이름의 수가 줄어드는 효용

<br/>


#### ⁜ " _강제_ ( _Coercion_ ) " 다형성
- 언어가 지원하는 자동 타입 변환 / 사용자 구현 타입 변환 등을 통해 동일한 연산자를 다양한 타입에 범용적으로 사용하는 방식
  - _ex. Java : 정수 + 정수 → 정수의 합 / 정수 + 문자열 → 문자열로 자동 강제 형변환 및 연결_
  - 위 예처럼 사용은 가능하지만 [오버로딩 다형성](#--오버로딩--overloading---다형성)과 함께 사용하면 실제 연산 시, 어떤 메서드가 호출될지 모호하기 때문에 위험하다. 


<br/>
<br/>

---

## "상속"의 양면성
상속의 목적은 코드 재사용이 아닌<br/>
프로그램을 구성하는 개념들을 기반으로<br/>
<u>다형성을 기능하게 하는 **타입 계층</u>을 구축**하기 위한 것

**＜ 상속 매커니즘 유관 개념 ＞**
1. [업 캐스팅 & 동적 바인딩](#업캐스팅--동적-바인딩--up-casting--dynamic-binding-)
2. [동적 메서드 탐색](#동적-메서드-탐색--다형성)
3. [`self` 참조 & `super` 참조]()

#### ※ 통합 예제
- 부모 클래스 `Lecture`
  - 점수 기반 합불 여부에 대한 연산과 그에 필요한 데이터들을 가짐
```java
/**
 * 부모 클래스 Lecture
 */
public class Lecture {
  private int passLimit;
  private String title;
  private List<Integer> scores;

  public Lecture(String title, int passLimit, List<Integer> scores) {
    this.title = title;
    this.passLimit = passLimit;
    this.scores = scores;
  }

  public List<Integer> getScores() {
    return Collections.unmodifiableList(scores);
  }

  // 평균 연산
  public double average() {
    return scores.stream()
            .mapToInt(Integer::intValue)
            .average().orElse(0);
  }

  // 결과 반환
  private String summary() {
    return String.format("Pass : %d Fail : %d", passCount(), failCount()) ;
  }

  private long passCount() {
    return scores.stream()
            .filter(score -> score >= passLimit)
            .count();
  }

  private long failCount() {
    return scores.size() - passCount();
  }
}

```
- 자식 클래스 `GradeLecture`
  - 점수 기반 등급 평가 연산과 그에 필요한 데이터를 가짐
  - 등급 데이터 클래스 : `Grade`
```java
public class Grade {
  private String name;
  private int upper, lower;

  public Grade(String name, int upper, int lower) {
    this.name = name;
    this.upper = upper;
    this.lower = lower;
  }

  public String getName() {
    return name;
  }
  public boolean isSameNameWith(String name) {
      return this.name.equals(name) ;
  }
  public boolean isIncludeIn(int score) {
      return score >= lower && score <= upper ;
  }
}

/**
 * 자식 클래스 GradeLecture 
 * - 생성시, 부모 클래스와 동일하게 가지는 데이터 등록
 */
public class GradeLecture extends Lecture {
  private List<Grade> grades ;
  
  public GradeLecture(String name, int passLimit, List<Grade> grades, List<Integer> scores) {
      super(name, passLimit, scores) ;
      this.grades = grades;
  }
  
  @Override
  public String summary() {
      return super.summary() + " | " + gradeStatistics() ;
  }
  
  private String gradeStatistics() {
      return grades.stream()
              .map(grade -> format(grade))
              .collect(joining(" ")) ;
  }
  private String format(Grade grade) {
      return String.format("%s:%d", grade.getName(), gradeCount(grade)) ;
  }
  // 부모 클래스의 getScores 를 그대로 이어받아 쓸 수 있음.
  private long gradeCount(Grade grade) {
      return getScores().stream()
              .filter(grade::isIncludeIn)
              .count();
  }
}
```

중요한 점은 `summary()`와 같은 메서드는 두 클래스에서 시그니처가 완전히 동일한데<br/>
부모 클래스와 자식 클래스에 <u>동일 시그니처의 메서드가 있다면</u><br/>
"**자식 클래스**의 메서드의 **우선순위가 더 <u>높다</u>**."

즉, `Lecture lecture = new GradeLecture(...)`의 방식으로 업캐스팅이 가능한데<br/>
만약 해당 `Lecture`에서 `summary()` 메서드를 실행시킨다면<br/>
`Pass : %d Fail : %d | %s:%d %s:%d` 형식으로 반환되는 것이다.

이와 같이 상속받은 메서드와 <u>**동일한 시그니처 메서드를 재정의**</u>하여<br/>
부모 클래스의 구현을 <u>새로운 구현으로 대체하는 것</u>을 "**메서드 오버라이딩**"이라 하는 것이다.

더 나아가 <br/>
부모 클래스에는 없던 **새로운 메서드를 추가하는 것**도 가능하며<br/>
<u>부모 클래스에 정의된 동일한 이름을 가지지만 서로 다른 시그니처의 메서드를 정의</u>( == **메서드 오버로딩** )할 수도 있다.

```java
public class GradeLecture extends Lecture {
    /**
     * 오버로딩 Overloading
     * - Lecture 클래스에 있는 average 메서드와는 다른 시그니처
     */
    public double average(String gradeName) {
      return grades.stream()
              .filter(each -> each.isSameNameWith(gradeName))
              .map(this.gradeAverage)
              .orElse(0d);
    }

    /**
     * 부모 클래스에는 없는 새로운 메서드 정의
     */
    public double gradeAverage(Grade grade) {
        return getScores().stream()
                .filter(grade::isIncludeIn)
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0);
    }
}
```
<br/>

### ✵ 데이터 관점
> "부모 클래스에서 정의한 **모든 데이터**"를 자식 클래스의 인스턴스에 자동으로 포함시킬 수 있다.

메모리 상에 생성된 GradeLecture의 인스턴스는 부모 클래스인 Lecture의 구성을 그대로 포함하고 있는 형태로 존재한다.

그에 따라 `Lecture lecture = new GradeLecture(...)`의 형식에서<br/>
`lecture`는 **`GradeLecture` 인스턴스를 참조**하기 때문에

내부에 내포하고 있는 `Lecture`에 직접 접근할 수 없으며<br/>
<u>해당 GradeLecture 인스턴스가 가지고 있는</u> **부모 클래스 Lecture 인스턴스의 자원들**은<br/>
**GradeLecture 객체만**이 **내부에서 접근 및 활용**이 가능하게 된다.

<br/>

### ✵ 행동 관점
> "부모 클래스에서 정의한 **일부 메서드**"를 자식 클래스의 인스턴스에 자동으로 포함시킬 수 있다.
> 
> ✵ _단, 구체적인 구현 방법 / 메모리 구조는 언어나 플랫폼에 따라 다를 수 있다는 점을 주의하자._

- 부모 클래스의 모든 `public` 메서드는 <u>**자식 클래스의 퍼블릭 인터페이스**에 포함</u>
  - <u>부모 클래스 인스턴스에게 전송 가능</u>한 메시지 → **자식 클래스 인스턴스에게도 전송 가능**
  - _Override를 하지 않더라도 부모 클래스 인스턴스에게 전송하던 메시지를 받으면 처리가 가능_

**런 타임**에 시스템은 <u>자식 클래스에 정의되지 않은 메서드</u>가 있을 경우,<br/>
" **부모 클래스 안**에서 탐색 "을 하는 구조이다.

<br/> 

#### ‼️ 메모리 할당 ‼️
- **[[ 객체 ]]** : **서로 다른 상태**를 저장 → <u>각 인스턴스별로 **독립적인 메모리**</u> 할당


- **[[ 메서드 ]]** : 동일한 클래스의 인스턴스끼리 **공유 가능** → <u>최초 메모리 로드 이후</u>, 각 인스턴스별로 **포인터** 부여

**포인터**를 갖는 구조를 자세히 살펴보면<br/>
각 인스턴스의 클래스는 모두 자신의 **부모 클래스에 대한 포인터**를 가지게 되는데<br/>
이와 같이 포인터를 활용하여 <u>클래스의 **상속 계층**을 순차적으로 따라올라가며 탐색</u>하는 것이다.<br/>
(_이러한 구조때문에 마치 자식 클래스에 부모 클래스의 복사본을 가지고 있는 것처럼 보이는 것_)

- "**최상위 부모 클래스인** `Object`"를 마지막으로 **상속 계층은 끝**

<br/>
<br/>

---
## 업캐스팅 & 동적 바인딩 ( _Up-Casting & Dynamic Binding_ )


<br/>
<br/>

---
## 동적 메서드 탐색 & 다형성


<br/>
<br/>

---
## 상속 vs 위임


<br/>
<br/>

---