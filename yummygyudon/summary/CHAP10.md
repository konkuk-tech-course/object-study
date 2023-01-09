# Chapter 10. 상속과 코드 재사용

객체 지향에서 클래스를 재사용하는 방법으로는 두 가지가 있다.
- "**상속**" : **클래스 안에 정의된 인스턴스 변수와 메서드**를 <u>자동으로 새로운 클래스에 **추가**</u>하는 구현 방법
- "**합성**" : 새로운 클래스 **인스턴스 안**에 <u>**기존 클래스**의 인스턴스를 **포함**</u>시키는 방법

이렇게 코드를 재사용하려는 목표은 결국 중복된 코드를 제거하려는 것이 목적이다.

## 상속과 중복 코드

### `DRY` 원칙
> "**D**on't **R**epeat **Y**ourself" ( 반복하지 말아라 )<br/>
>  : 동일한 것. 즉, 동일한 지식을 중복하지 말아라. 
> 
> 모든 지식은 시스템 내에서 단일하고, 애매하지 않으며 확고히 믿을만한 표현 양식을 가져야 한다.
> 
> _* " **한 번, 단 한번**( **Once and Only Once** ) 원칙 "" / " **단일 지점 제어**( **Single-Point Control** ) 원칙 "이라고도 불린다._

**중복 코드**가 <u>변경</u>에 **방해 요소**가 되는 것이 <br/>
중복 코드 제거에 있어 가장 큰 이유이다.

비즈니스와 관련된 지식을 코드로 변환하게 되는데<br/>
지식은 항상 바뀌기에 그에 따라 코드도 변할 수 밖에 없다.<br/>
따라서 <u>새로운 코드를 추가하고 나면 언젠가는 변경될 것이라고 생각</u>하는 것이 바람직하다.

어떤 코드가 중복인지 찾는 것부터 시작해<br/>
찾더라도 모든 코드를 일관되게 수정해야 한다.<br/>
심지어 개별적으로 테스트했을 때도 동일한 결과가 나와야 한다.

이처럼 중복 코드로 인해 **수정과 테스트에 드는 비용**이 증가되고<br/>
시스템과 개발자 모두 **곤경에 처하는 상황**을 피할 수 없다.

여하튼 <u>중복 여부를 판단하는 기준</u>은 "**변경**"이다.<br/>
예를 들어,<br/>
**요구사항이 변경됨**에 따라 <u>두 코드를 함께 수정해야 한다면</u> **중복**이다.<br/>
당연히 함께 수정할 필요가 없으면 중복이 아니다.<br/>
단순히 코드의 모양은 가능성이기 때문에 모양만으로 판단해선 안된다.

즉, 중복 여부를 결정하는 기준은 "<u>**코드가 변경에 반응하는 방식**</u>"이다.

### 변경
중복 코드는 <u>**새로운 중복 코드**를 야기할 가능성</u>이 높다.<br/>
중복 코드를 제거하지 않은 상태에서 코드를 수정할 수 있는 유일한 방법은<br/>
새로운 중복 코드를 추가하는 것뿐이기 때문이다.

이에 따라 새로운 중복 코드를 추가하는 과정에서<br/>
예상치 못하게 <u>**코드의 일관성**이 무너질 위험</u>이 도사리고 있으며<br/>
더 큰 문제는<br/>
중복 코드가 늘어날수록 <u>애플리케이션은 **변경**에 **취약**</u>해지고 <u>**버그 발생 가능성**</u>이 높아진다.<br/>
( _**코드의 양**과 **버그 발생 가능성**은 <u>비례</u>한다._ )

즉, 민첩한 변경을 위해서는 중복 코드 추가가 아닌 **제거가 필요**하다.<br/>
( _항상 코드를 DRY하게 만들기 위해 노력해야 한다._ )

<br/>

두 클래스 사이의 중복 코드를 제거하는 방법 중 하나는<br/>
" <u>클래스를 하나로 합치는 것</u> "이다.

즉, 코드가 중복되게끔 만든 분리의 기준 값을 찾아 하나의 타입으로 만드는 것이다.<br/>
하지만 타입으로 사용되는 클래스는 **낮은 응집도와 높은 결합도**라는 문제가 생기기 마련이다.

이 때, 사용되는 것이 바로 " <u>**상속**</u> "이다.

<br/>

### 상속 활용

상속의 기본 효용은<br/>
" <u>이미 존재하는 클래스와 유사한 클래스가 필요하다면 **코드를 복사하지 말고 상속을 활용**해 코드를 재사용하라는 것이다.</u> "

<br/>

단, 클래스의 상속 계층이 매우 깊어지게 된다면<br/>
<u>이해하기 어려운</u> **재사용을 위한 가정**들이 생겨난다.

**결합도**가 하나의 모듈이 다른 모듈에 대해 얼마나 많은 지식을 갖고 있는지를 나타내는 척도인데<br/>
상속을 이용해 코드를 재사용할 경우,<br/>
부모 클래스를 만들 때 사용했던 많은 가정이나 추론 과정을<br/>
자식 클래스에서 **부모 클래스를 상속받아 만들 때도** 그 <u>모든 정보를 완벽히 이해</u>하고 있어야 한다.

즉, <u>알아야 하는 지식이 많을수록 결합도가 높아짐</u>에 따라<br/>
상속은 <u>부모 클래스와 자식 클래스 사이</u>의 **강한 결합도**가 형성되고 그만큼 **코드를 수정하기 어려워진다**.

어쩌면 당연하다.<br/>
Override 하는 방식은 부모 클래스의 것을 그대로 받아와서 사용하는 `super`를 사용하는 방식이다.

그렇기 때문에 Override를 하고자 할 때,<br/>
부모 클래스에서 사용하는 로직이 조금이라도 <u>자식 클래스에서 원하는 처리와 다를 경우</u><br/>
**새로운 중복코드가 생길 가능성**이 생긴다.

또한 만약 부모 클래스에서 **로직이 조금이라도 바뀐다면**<br/>
자식 클래스에서 기존의 부모 로직과 <u>강한 결합도를 가진 코드들</u>에 **문제가 생길 가능성**이 높다.

이와 같이<br/>
" _<u>**상속 관계로 연결된 자식 클래스**가 <u>부모 클래스의 변경에 취약</u>해지는 현상</u>_ "을<br/>
`취약한 기반 클래스 문제`라고 부른다.

<br/>

---
## 취약한 기반 클래스 문제
상속은 자식 클래스와 부모 클래스의 결합도를 높이고<br/>
그에 따라 강한 결합도로 인해 자식클래스는 <u>부모 클래스의 불필요한 세부사항</u>에 **종속**된다.

부모 클래스의 작은 변경에도 자식 클래스는 오류와 에러에 시달릴 수 밖에 없는데<br/>
이와 같이 " <u>상속이라는 문맥 안에서 결합도를 초래하는 문제점</u> "을 <br/>
" <u>**취약한 기반 클래스 문제**</u>(**Fragile Base Class Problem, Brittle Base Class Problem**) "이라고 한다.

물론 <u>자식 클래스를 점진적으로 추가해서 **기능을 확장**하는 데</u>는 용이하지만<br/>
높은 결합도로 인해 **부모 클래스를 점진적으로 개선하는 것이 어렵다**.

본래 객체를 사용하는 이유가<br/>
" _<u>구현과 관련된 세부사항을 퍼블릭 인터페이스 뒤로 캡슐화를 할 수 있다는 특징</u>_ "을 가지고<br/>
이에 따라 **변경에 의한 파급 효과를 제어**할 수 있기 때문이다.

하지만 상속을 사용하게 되면<br/>
부모 클래스의 퍼블릭 인터페이스가 아닌 구현을 변경하더라도 **자식 클래스는 변경에 의한 파급 효과가 발생하기 쉬워진다**.<br/>
즉,상속은 코드의 재사용을 위해 <u>**캡슐화의 장점**을 약화</u>시키고 <u>**구현에 대한 결합도**를 높임</u>으로써<br/>
객체지향의 강점을 반감시키는 것이다.

다양한 문제들 중 하나를 꼽아보자면<br/>
Override 시, `super`를 통해 부모 메서드의 내용을 상속받아 사용하게 되는데<br/>
부모 메서드의 구현을 직접적으로 보지 못하고 결국 퍼블릭 인터페이스를 사용하게 됨으로써<br/>
자칫 로직을 잘못짜면 <u>**중복 연산</u>이 발생**할 수 있는 위험성이 있다.

그렇다고 부모 클래스에서 해당 메서드를 삭제할 수도 없는 노릇이다.<br/>
이에 따라 발생할 수 있는 예외에 대해서 자식 메서드의 로직을 더 세세하게 짜는 방법도 있지만<br/>
이 또한 결국 중복 코드일 가능성도 높기 때문에<br/>
아이러니 하게도 **중복 코드를 줄이기 위해 중복 코드를 작성하는 모순**이 생길 수 있는 것이다.

> #### ⁜ 상속을 위한 경고
> 1. 자식 클래스의 메서드 안에서 `super` 참조를 이용해 **부모 클래스의 메서드를 직접 호출**할 경우,<br/> 
>   두 클래스는 **강하게 결합**된다.<br/>
>   <u>`super` 호출을 제거할 수 있는 방법</u>을 찾아 **결합도를 제거**하라.<br/></br>
> 2. 상속받은 <u>부모 클래스의 메서드</u>가 **자식 클래스**의 <u>내부 구조에 대한 규칙을 깨트릴 수 있다</u>.<br/></br>
> 3. 자식 클래스가 부모 클래스의 **메서드를 오버라이딩**할 경우 ,<br/>
>   부모 클래스가 **자신의 메서드를 사용하는 방법**에 <u>자식 클래스가 결합될 수 있다</u>.<br/></br>
> 4. 클래스를 상속하면 결합도로 인해 자식 클래스와 부모 클래스의 <u>구현을 영원히 변경하지 않거나</u>,<br/>
>   자식 클래스와 부모 클래스를 <u>동시에 변경</u>하거나 <br/>
>   둘 중 하나를 선택할 수 밖에 없다.
 
<br/>

## 문제 해결
아쉽게도 상속을 활용함에 있어 취약한 기반 클래스를 문제를 완전히 없앨 수는 없지만<br/>
어느 정도까지 위험을 완화시키는 것은 가능하다.

그 문제 해결의 열쇠는 이번에도 어김없이 " **추상화** "이다.

### 추상화 의존
강한 결합도로 인해 변경될 가능성이 높아지는 현상에 대한 일반적인 해결법은
자식 클래스가 부모 클래스의 구현이 아닌 추상화에 의존하도록 만드는 것이다.

즉, 부모 클래스와 자식 클래스 모두 추상황에 의존하도록 하는 것이 좋다.

- 두 메서드가 유사해 보인다면 "**차이점**"을 <u>**메서드**로 추출</u>한다.<br/>
  메서드 추출을 통해 두 메서드를 동일한 형태로 보이도록 만들 수 있다.


- 부모 클래스의 코드를 하위로 내리지 말고 **자식 클래스의 코드**를 <u>**상위**로 올려야 한다</u>.<br/>
  부모 클래스의 구체적인 메서드를 자식 클래스로 내리는 것보다<br/>
  **자식 클래스**의 <u>**추상적**인 메서드를 **부모 클래스로 올리는 것**</u>이 **재사용성**과 **응집도** 측면에서 뛰어난 결과를 얻을 수 있다.

### Extract Difference to Method (차이를 메서드로 추출)
직전에 언급했듯이<br/>
가장 먼저 중복 **코드 안에서 차이점을 별도의 메서드로 추출**하는 것이다.

즉, " _<u>변하는 것으로부터 **변하지 않는 것을 분리**하라.</u>_" / " _<u>**변하는 부분**을 찾고 이를 **캡슐화**하라.</u>_ " 라는 조언에 대해<br/>
**메서드 수준**으로 실현시키는 것이다.

```java
// Class A
public Money calculateFee() {
    Money result = Money.ZERO;
    
    // 인스턴스 변수로 Money amount, Duration seconds, List<Call> calls 가 있다.
    for(Call call : calls) {
        result = result.plus(amount.times(call.getDuration().getSeconds() / seconds.getSeconds()));
    }
    return result;
}

// Class B
public Money calculateFee() {
    Money result = Money.ZERO;

    // static 상수로 LATE_NIGHT_HOUR = 22가 있다.
    // 인스턴스 변수로 Money nightlyAmount, Money regularAmount, Duration seconds, List<Call> calls 가 있다.
    for(Call call : calls) {
        if(call.getForm().getHour() >= LATE_NIGHT_HOUR){
            result=result.plus(
                nightlyAmount.times(call.getDuration().getSeconds()/seconds.getSeconds())
            );
        } else {
            result=result.plus(
                regularAmount.times(call.getDuration().getSeconds()/seconds.getSeconds())
            );
        }
    }
    return result;
}
```
위와 같이 유사해보이는 메서드가 있을 경우,<br/>
**다른 부분**만 <u>별도의 메서드로 추출</u>하는 것이다.

```java
// Class A & Class B 통일화 메서드(퍼블릭 인터페이스)
public Money calculateFee(){
    Moeny result = Money.ZERO;

    for(Call call : calls) {
        result = result.plus(calculateCallFee(call));
    }
    return result;
}

// Class A 의 calculateFee()
private Money calculateCallFee(Call call){
    return amount.times(call.getDuration().getSeconds() / seconds.getSeconds());
}
// Class A 의 calculateFee()
private Money calculateCallFee(Call call){
    if(call.getForm().getHour() >= LATE_NIGHT_HOUR){
        return nightlyAmount.times(call.getDuration().getSeconds()/seconds.getSeconds());
    } else {
        return regularAmount.times(call.getDuration().getSeconds()/seconds.getSeconds());
    }
}
```

### 중복 코드는 부모 클래스로
위 예시를 그대로 사용한다면<br/>
다음 순서로 해야할 작업은 **부모 클래스에 올리는 작업**이다.

**공통 부분**은 <u>퍼블릭 인터페이스</u>로 설정하고<br/>
**차이에 대하여 추출된 메서드**인 `calculateCallFee(Call call)`을 <u>추상메서드</u>로 만들면 된다.

```java
// 클래스 구조
public abstract class AbstractPhone{}
public class Phone extends AbstractPhone {}
public class NightlyDiscountPhone extends AbstractPhone {}
```
```java
// 클래스 구조
public abstract class AbstractPhone{
  private List<Call> calls = new ArrayList<>();
  
  public Money calculateFee(){
    Moeny result = Money.ZERO;

    for(Call call : calls) {
      result = result.plus(calculateCallFee(call));
    }
    return result;
  }
  
  abstract Money calculateCallFee(Call call);
}
public class Phone extends AbstractPhone {
  private Money amount ;
  private Duration seconds ;

  // ...(Constructor)

  @Override
  protected Moeny calculateCallFee(Call call){
    return amount.times(call.getDuration().getSeconds() / seconds.getSeconds());
  }
}
public class NightlyDiscountPhone extends AbstractPhone {
  private static final int LATE_NIGHT_HOUR = 22;
  
  private Money nightlyAmount ;
  private Money regularAmount ;
  private Duration seconds ;
    
  // ...(Constructor)
  
  @Override
  protected Moeny calculateCallFee(Call call){
    if(call.getForm().getHour() >= LATE_NIGHT_HOUR){
      return nightlyAmount.times(call.getDuration().getSeconds()/seconds.getSeconds());
    } 
    return regularAmount.times(call.getDuration().getSeconds()/seconds.getSeconds())
  }  
}
```
이와 같이 자식 클래스들 사이의 공통점을 부모 클래스로 옮김으로써<br/>
실제 코드를 기반으로 상속 계층을 구성할 수 있고<br/>
이에 따라 설계는 **추상화에 의존**하게 된 것이다.

자식 클래스 뿐만 아니라<br/>
부모 클래스도 자신의 내부 구현 추상 메서드를 호출하기 때문에 추상화에 의존하게 되는 구조이다.

그에 따라<br/>
부모 클래스를 **상속받는 새로운 클래스가 추가**된다면 <br/>
<u>다른 클래스를 수정할 필요 없이</u> `calculateCallFee`메서드만 오버라이딩하면 된다.<br/>
즉, <u>확장에는 열려 있고 수정에는 닫혀</u> 있기에 "**개방-폐쇄 원칙**" 또한 준수하게 되는 것이다.

### 의도를 드러내는 네이밍
위 예시에서 아쉬운 점이 있다면<br/>
바로 클래스들의 이름이다.

추상화와 같은 개념의 명칭이 객체 클래스 이름으로 사용되는 것은 <br/>
해당 클래스의 **내용을 명시적으로 표현하지 않기 때문에** 좋은 이름이 아니다.

또한 부모와 자식 관계로 확장할 때도<br/>
이름이 어느정도 **관계를 파악하기 용이**해야 한다.

그에 따라 위 예시들의 이름을 다시 바꿔본다면 아래와 같이 바꾸는 것이 바람직하다.
- `AbstractPhone` → `Phone`
- `Phone` → `RegularPhone`
- `NightlyDiscountPhone` → `NightlyDiscountPhone`

<br/>

---
## 차이에 의한 (`Programming By Difference`)
해당 단원에서 취약점을 주로 다르긴 했지만<br/>
사실 상속을 사용하면 이미 존재하는 클래스의 코드를 기반으로 다른 부분을 구현함으로써<br/>
새로운 기능을 쉽고 빠르게 추가할 수 있다는 강점이 있다.

이처럼 <u>기존 코드와 다른 부분만을 추가함으로써 애플리케이션의 기능을 확장</u>하는 방법을<br/>
" <u>**차이에 의한 프로그래밍**</u>(programming by difference)"라 부른다.

이 방법의 목표는<br/>
**중복 코드를 제거하고 코드를 재사용하는 것**이고<br/>
프로그래밍 세계에서 악의 근원인 중복 코드를 제거할 수 있다는 효용 가치가 있기에<br/>
강력한 도구라 할 수 있다.

하지만 코드를 재사용하기 위해 맹목적으로 상속을 사용하는 것은 위험하다.<br/>
중복 코드 제거라는 강력함이 있지만<br/>
그만큼의 반작용으로 큰 피해가 발생한다.

상속의 오용과 남용으로 인해 <u>애플리케이션을 이해하는 것과 더불어<br/>
오히려 확장하기 어려워지는 피해</u>가 발생한다.

그렇기 때문에 정말 필요한 상황이 아니라면 상속이 아닌<br/>
" <u>**합성**</u> "이라는 더 좋은 방법을 사용하는 것이 좋다.