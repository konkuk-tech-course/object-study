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
- " [**업캐스팅**(Up-Casting)](#업-캐스팅) " <br/>: `Lecture lecture = new GradeLecture(...)`의 형식과 같이<br/>
**부모 클래스 타입으로 선언된 변수**에 <u>자식 클래스의 인스턴스를 할당하는 것</u>이 가능
  - <u>서로 다른 클래스 인스턴스를 **동일한 타입에 할당**하는 것</u>을 가능케 함.<br/> → 부모 클래스 <u>코드 수정 없이</u> **자식 클래스 적용** 가능


- " [**동적 바인딩**(Dynamic Binding)](#동적-바인딩) " <br/> : 선언 변수 타입이 아니라 **실제 실행시점**에 <u>메시지를 수신하는 객체의 타입에 따라</u> **실행되는 메서드가 결정**되는 것
  - <u>부모 클래스 타입에 대해 메시지</u>를 전송하더라도 실행 시에 실제 클래스를 기반으로 실행될 메서드가 결정<br/> → <u>코드 수정 없이</u> **실행 메서드 동적 변경** 가능

<br/>

### ⇋ 같은 "메시지" - 다른 "메서드"
`업캐스팅`과 `동적 바인딩`이라는 매커니즘에 의해<br/>
<u>_코드 내에 선언된 참조 타입과는 무관하게_</u><br/> 
**실제로 메시지를 수신하는 객체의 타입**에 따라 실행되는 메서드가 달라질 수 있는 것이고

그에 따라 "<u>동일한 수신자</u>에게 <u>동일한 메시지</u>를 전송"하는 <u>동일한 코드</u>를 이용해<br/>
**서로 다른 메서드**를 **실행**할 수 있는 것이다.

<br/>

### 업 캐스팅
- " 명시적인 타입 변환 없이 " <br/>**부모 클래스 타입 참조 변수**에 <u>자식 클래스 인스턴스 **대입**</u> 허용<br/>& **부모 클래스 타입 파라미터**에 <u>자식 클래스 인스턴스 **전달**</u> 허용
  - 단, 반대로 `부모 클래스 인스턴스` → `자식 클래스 타입 참조 변수 / 자식 클래스 타입 매개변수` 의 경우에는 **명시적인 <u>타입 캐스팅**</u>이 필요<br/> == "**다운 캐스팅(Down-Casting)**"
    - ```java
      Lecture lecture = new G임radeLecture(...);
      GradeLecture gradeLecture = (GradeLecture)lecture ;
      ```

컴파일러의 과점에서
자식 클래스는 아무런 제약 엾이 부모 클래스를 대체할 수 있기 때문에
부모 클래스와 협력하는 클라이언트는 
다양한 자식 클래스에 대해 모르더라도 모든 자식 클래스의 인스턴스와도 협력이 가능하다.

<br/>

### 동적 바인딩
> ※ **객체지향 언어** - 메서드 실행 : "**<u>메시지</u>**" 전송<br/><br/>
> ※ _전통적 언어 - 메서드 실행 : 메서드 호출_

- **동적** 바인딩 (`Dynamic Binding`) / **지연** 바인딩 (`Lazy Binding`) / **런 타임** 바인딩 (`Run-time Binding`)
  - "**객체지향 언어**"들의 함수 호출 방식
  - `foo.bar()` 형식의 호출 <br/>→ `foo`가 가르키는 객체가 <u>어떤 클래스의 인스턴스인지</u> & `.bar()`가 <u>어느 상속 계층에 위치하는지</u> 알아야함.
  - 실행될 메서드가 **<u>런타임</u>에 판단되어 결정**되는 방식

<br/>

- **정적** 바인딩 (`Static Binding`) / **초기** 바인딩 (`Early Binding`) / **컴파일 타임** 바인딩 (`Compile-time Binding`)
  - 전통적인 언어들의 함수 호출 방식
  - **코드 내 명시적인** `bar 함수`를 통해 호출 → 실제 실행

<br/>

> ✅ **객체지향 언어** - `Up-Casting` & `Dynamic Binding`<br/>
> → " 부모 클래스 참조에 대한 **메시지 전송** "을 <u>**자식 클래스**에 대한 메서드 호출</u>로 **변환 가능**

<br/>
<br/>

---
## 동적 메서드 탐색 & 다형성

> 📍 실행 **메서드 결정 규칙**
> <br/>
> 
> 1. 메시지를 수신한 객체 → **자신을 생성한 클래스 내**에 적합한 메서드가 존재하는 검사
>    1. 존재 : 메서드 실행 & 탐색 종료
>    2. 미존재 : 다음 단계
> 2. **부모 클래스** 검사 → _적합한 메서드 <u>찾을 때까지 **상속 계층**</u> 검사_
> 3. 상속 계층 <u>최상위 클래스에도 없을 경우</u> → **예외 발생** & 탐색 종료
> 
> <br/>
>
> 메시지 수신 - (컴파일러) → "`self`**참조 (self reference)**" 임시 변수 자동 생성 → 메시지 수신 객체 포인터 설정<br/>
> ( **동적 바인딩** : <u>self가 가르키는 객체의 클래스</u>에서 시작해 **상속 계층의 역방향**으로 진행 )
> 
> - **정적 타입 언어**(_Java, C++, C#_) : `this`

시스템은 class 포인터 & parent 포인터와 함께 self 참조를 조합하여 메서드를 탐색한다.

- 메서드 탐색 : **자식 → 부모**
  - 항상 자식 클래스의 메서드를 먼저 탐색
  - 자식 클래스의 메서드가 **더 높은 우선순위**를 가지는 이유


### 탐색 원리 1 : " 자동적인 메시지 위임 "
>자식 클래스는 <u>자신이 **이해할 수 없는 메시지**(처리할 수 없는 호출)</u>를 전송받은 경우,<br/>
> 상속 계층을 따라 **부모 클래스에게 처리 위임**

자식 클래스에서 부모 클래스 방향으로 자동 메시지 처리 위임 <br/>
→ " _자식 클래스에서 어떤 메서드를 구현하고 있느냐_ "에 따라 부모 클래스에 구현된 메서드 운명 결정
- { **동일 시그니처** & 동일 이름 } : `Overriding` → 부모 클래스의 메서드 감춤( 먼저 탐색 )


- { **서로 다른 시그니처** & 동일 이름 } : `Overloading` → 상속 계층에 걸쳐 **공존**
  - 특정 메시지에 대해 **각 객체마다의 적절한 시그니처**를 가져야할 때 사용
  - ♨️ `Java` 는 " <u>**상속 계층 사이**의 메서드 오버로딩</u> " 까지도 **허용**<br/>
    ( _C++의 경우, <u>같은 클래스 내 메서드 오버로딩</u>까지만 허용_ )
  - _**언어**에 따라 방식이 상이할 수 있다._

<br/>

### 탐색 원리 2 : " 동적인 문맥 "
> 메시지 수신 시,<br/>
> "**실행 시점**"에 실제 어떤 메서드를 실행할지 결정 & "`self 참조`"를 통한 **탐색 경로** 결정

동일한 코드더라도<br/>
`self 참조`가 가리키는 객체가 무엇인지에 따라<br/>
메서드 탐색을 위한 상속 계층의 범위가 동적으로 변한다.

→ `self 참조`가 **가리키는 객체의 타입을 변경**함으로써 객체가 <u>실행될 문맥을 동적으로 바꿀 수 있다</u>.

<u>**자신에게 다시** 메시지를 전송</u>하는 `self 전송`으로 인해<br/>
종종 어떤 메서드가 실행될지 예상하기 어렵다.

<br/>

#### 💢 상속 관계에서의 `self 전송`
> ⁜ **다시 한번 강조** : `self 참조`가 가리키는 **바로 그 객체에서부터** 메시지 **탐색 재시작**

일반적으로 하나의 객체 내에서 `self 전송`이 발생할 경우에는 어렵지 않지만<br/>
" **상속 관계** "에서부터 곤란해지는 문제가 발생한다.

" _클라이언트에서 `bar()` 호출 메시지를 `Child` 클래스에 전달했다._ "

만약 1️⃣ " `bar()` 메서드에서 <u>`method()` 메서드를 `self 전송`</u> "하는 구조이고<br/>
2️⃣ `bar()` 메서드는 `Child` 클래스가 아닌 <u>부모 클래스인 `Parent` 클래스에</u> 있으며<br/>
3️⃣ `Child`와 `Parent` 클래스에 모두 <u>`method()` 메서드가 오버로딩</u> 되어있다고 가정하자.

<br/>

우선 `bar()` 메서드는 `Child` 클래스에는 없기 때문에 **부모 클래스로 위임**하였고<br/>
그에 따라 부모 클래스인 `Parent`에서 `bar()` 메서드를 찾았지만 <br/>

<u>`self 참조`는 여전히 `Child` 클래스</u>이기 때문에<br/>
`Child`부터 `method()`에 대한 **재탐색이 시작**된다.

<br/>

즉, 결과적으로 `bar()` 실행은 `Parent`에서하고 내부 `method()`는 `Child`에서 하게되는 상황이 연출되기때문에<br/>
**오버로딩 / `self 참조` 을 주의**해서 사용하거나
`super 참조`를 **잘 사용**해야 한다.

<br/>

> 📍언어 타입 별 **메서드 탐색 실패 예외**
> - **정적** 타입 언어 : `컴파일 에러` 발생
> - **동적** 타입 언어 : (컴파인 단계가 없어서 **실제 실행 전엔 판단 불가**) _언어마다 상이_
>   - 상속 계층 안의 어떤 클래스도 처리할 수 없어 최상위 클래스까지 모두 탐색하더라도 불가능하다면 최종적으로 예외 발생
>   - 단, **이해할 수 없는 메시지(요청)에 대해 응답할 수 있는 메서드**를 구현할 수 있다.
>     - 객체는 <u>자신의 인터페이스에 정의되지 않은 메시지 처리</u>가 가능해진다.

<br/>


### `self` vs `super`

- `self` 참조
  - 메시지 수신 객체의 클래스에 따라 메서드 탐색 문맥을 "**실행시점**"에 동적으로 결정
- `super` 참조
  - " 메서드 탐색을 <u>현재 클래스의 **부모 클래스**부터</u> 시작 " 으로 결정 
    - 직속 부모 클래스에서 못찾는다면 **더 상위 부모 클래스로** 이동하면서 검사
  - 자식 클래스에서 <u>**부모 클래스**의 **인스턴스 변수/메서드**에 접근</u>하기 위해 `super`참조라는 **내부 변수** 제공
  - 자식 클래스에서 부모 클래스의 구현을 재사용하는 경우
  - `super` 참조를 통해 메세지를 전송하는 것 → "`super` **전송**"
    - `super` 전송 : **항상** <u>해당 클래스의 부모 클래스에서부터</u> 메서드 탐색을 시작한도록 "**컴파일 시점"에 미리 결정**하는 방식


<br/>
<br/>

---
## 상속 vs 위임
> - _<u>동일한 타입의 객체 참조</u>에게 <u>동일한 메시지를 전송</u>하더라도_ <br/>" `self` **참조가 가리키는 객체의 클래스**가 무엇이냐 "에 따라 <u>메서드 탐색 문맥이 달라짐</u> 
> - **동적 문맥의 흐름** : 자식 클래스에서 **부모 클래스로** <u>`self` 참조를 전달</u>하는 매커니즘

### 위임 & `self` 참조
- `self` 참조 : **항상** 메시지를 **수신한 객체** 
  - "**메세드 탐색**" 중 → 자식 클래스 & 부모 클래스 인스턴스들이 <u>모두 동일한 `self` **참조 공유**</u>

자식 클래스의 메서드이든 부모 클래스의 메서드이든
실행되는 메서드가 <u>특**정 클래스 메서드가 아닐수도** 있다</u>는 것을 **명시**하는 의미와 다를바 없다.

→ `self` 참조로부터 메서드 탐색 과정을 통해<br/>
**자신이 수신한 메시지를** <u>그대로 다음 탐색 대상에게 동일하게 **메시지를 전달**</u>한다.

즉, "자신이 정의하지 않거나 처리할 수 없는 속성 또는 메서드의 <u>탐색 과정을 다른 객체로 이동 시키는 것</u>"<br/>
== " **위임(Delegation)** "

<br/>

> - **포워딩** : `self` 참조 전달 ❌
>   - 단순히 코드 재사용을 위한 경우
> - **위임** : `self` 참조 전달 ⭕️
>   - 클래스를 이용한 상속 관계를 <u>객체 사이의 **합성관계**</u>로 대체 → "**다형성**" 구성
> 
> ※ "상속" → **자식 클래스로 선언**하면 <u>자동으로 실행 시</u>에 인스턴스들 사이에서<br/> "`self` 참조가 전달 및 동일 문맥 공유 :: **위임** "를 하게 해준다.


<br/>

### "프로토 타입" 기반 _O.O.L ( Object-Oriented Language )_

모든 예제가 클래스 기반 객체지향 언어인 Java에 초점이 맞춰져있어 알지 못했겠지만<br/>
위임을 포함한 **객체 지향 패러다임**은 <u>클래스가 없더라도 **구현이 가능**</u>하다.

> 📍 **핵심**
> - 만약 " _객체의 개인적인 특징이 메시지 응답에 부적합하다면_ "<br/> 객체는 응답할 수 있는지 여부를 알기 위해 <u>**메시지**를 "**프로토 타입**"에 **포워딩**</u>한다.
> 
> 
> - 메시지가 계속해서 위임된다면 <br/> <u>변수의 값에 대한 모든 **질문**</u> 이나 <u>메시지에 응답할 모든 **요청**들</u>은 메시지를 위임했던 <br/> **원래의 객체**에 의해 **먼저 추론**되어야 한다.

☞ `JavaScript` 예시 
```javascript
function  Lecture(name, scores) {
    this.name = name ;
    this.scores = scores ;
}

/**
 * 여기에서의 prototype은
 * 최상위 객체 타입인 Object로 취급
 */
Lecture.prototype.stats = function () {
    return "Name: " + this.name + ", Evaluation Method: " + this.getEvaluationMethod() ;
}

Lecture.prototype.getEvaluationMethod = function () {
  return "Pass or Fail" 
}


function  GradeLecture(name, canceled, scores) {
  Lecture.call(this, name, scores) ;
  this.canceled = canceled ;
}

/**
 * GradeLecture의 prototype을 "Lecture 인스턴스"로 할당
 * "prototype"이라는 연결고리로 상속 관계 형성
 */
GradeLecture.prototype = new Lecture();

GradeLecture.prototype.constructor = GradeLecture ;

/**
 * 오버라이딩
 */
GradeLecture.prototype.getEvaluationMethod = function () {
  return "Grade"
}
```

클래스 없이 **프로토타입 체인**을 통해 " <u>**자동적**으로 **메시지 위임**이 발생</u>하는 구조 "가 형성된다.<br/>
( _탐색 과정은 클래스 기반 객체 지향 언어의 경우와 다를 바 없다._ )

즉, 객체들 사이에서 다형성을 형성하기 위해서 필요한 것은<br/>
클래스가 아닌 "**관계**"와 "**메시지 위임**"인 것이다.

<br/>
<br/>

---

## ⭐️ 한 마디 ⭐️
> "객체 지향"은 **"객체"를 지향하는 것**
> - 클래스(Class) : 객체를 편하게 정의하고 생성하기 위해 제공되는 프로그래밍 구성 요소
>   - _클래스 없이도 객체 사이의 협력 관계 구축 가능 ([프로토 타입 기반 언어](#-프로토-타입--기반-ool--object-oriented-language-))_
> 
> ※ " <u>**메세지**</u> " & " <u>**협력**</u> " 이 가장 중요