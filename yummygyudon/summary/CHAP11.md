# Chapter 11. 합성과 유연한 설계
- `상속` : 부모 클래스와 자식 클래스를 연결해서 **부모 클래스의 코드를 재사용**하는 방법
    - `is -a` 관계
    - 재사용 대상 : `부모 클래스`
    - 부모 클래스와 자식 클래스 사이의 의존성은 "**컴파일 타임**"에 해결


- `합성` : **전체를 표현하는 객체**가 <u>부분을 표현하는 객체를 **포함**</u>해서 **부분 객체의 코드를 재사용**하는 방법
    - `has -a` 관계
    - 재사용 대상 : `인터페이스`
    - 두 객체 사이의 의존성은 "**런 타임**"에 해결

합성은 상속과 다르게<br/>
구현에 의존하지 않고 퍼블릭 인터페이스에 의존한다.

그에 따라<br/>
포함된 객체의 내부 구현이 변경되더라도 영향을 최소화할 수 있기 때문에<br/>
변경에 더 안정적인 코드를 얻을 수 있다.

<br/>

합성 관계는 <u>실행 시점에 **동적**으로 변경</u>할 수 있기 때문에<br/>
상속 대신 합성을 사용하면 변경하기 쉽고 유연한 설계를 얻을 수 있다.

> - " **서브 클래싱** "에 의한 재사용 : <u>**화이트 박스**</u> 재사용(**White-Box** Reuse)
> - " **인터페이스** "에 의한 재사용 : <u>**블랙 박스**</u> 재사용(**Black-Box** Reuse)


## 상속 → 합성
> #### 상속 남용의 문제점
> - ⟦ [**불필요한 인터페이스 상속**](#javautilproperties--javautilstack) ⟧
>   - 부적합한 <u>부모 클래스의 오퍼레이션까지 상속</u>
>   - 자식 클래스 인스턴스 상태 불안정
> - ⟦ [**메서드 오버라이딩 오작용**](#instrumentedhashset) ⟧
>   - 오버라이딩할 때 자식 클래스가 부모 클래스의 메서드 호출 방법에 영향
> - ⟦ [**부모 클래스와 자식 클래스의 동시 수정**](#동시-수정) ⟧
>   - <u>강한 개념적 결합</u>으로 인해 함께 수정

합성을 통해 위 문제점을 해결할 수 있다.


### `java.util.Properties` & `java.util.Stack`

`Properties`클래스는 기본적으로 `Hashtable` 클래스를 상속 받는 관계인데<br/>
이를 <u>"**인스턴스 변수**"로 **포함**</u>시키게 되면 합성 관계로 변환할 수 있다.
```java
import java.util.Hashtable;

// extends 삭제
public class Properties {
  private Hashtable<String, String> properties = new Hashtable<>();
  
  public String setProperty(String key, String value){
      return properties.put(key, value);
  }
  public String getProperty(String key){
      return properties.get(key);
  }
}
```
이를 통해 `Properties`의 클라이언트는 <u>오직 `Properties`에서 **정의한 오퍼레이션만 사용**</u>할 수 있다.<br/>
또한 Properties의 클라이언트는 모든 타입의 키와 값을 저장할 수 잇는 <u>인스턴스 변수 Hashtable에 **접근할 수 없기** 때문에</u><br/>
Properties의 **규칙을 어길 위험성**도 사라진다.

<br/>

`Stack`클래스도 마찬가지다.<br/>
부모 클래스인 `Vector`의 인스턴스 변수를 Stack 클래스의 인스턴스 변수로 선언하여 <br/>
합성 관계로 전환할 수 있고

이에 따라 Stack 클래스의 퍼블릭 인터페이스에는 불필요한 Vector 클래스의 오퍼레이션들이 포함되지 않는다.

```java
import java.util.EmptyStackException;
import java.util.Vector;

public class Stack<E> {
  private Vector<E> elements = new Vector<>();

  public E push(E item) {
    elements.addElement(item);
    return item;
  }

  public E pop() {
    if (elements.isEmpty()) {
      throw new EmptyStackException();
    }
    return elements.remove(elements.size()-1);
  }
}
```

<br/>

### `InstrumentedHashSet`
`InstrumentedHashSet`클래스는 `HashSet`클래스를 상속 받는 클래스이다.

앞서 알아봤던 방법은 부모 클래스를 인스턴스 변수로 가짐으로써<br/>
불필요한 오퍼레이션 상속을 피할 수 있었다. 

하지만 이번 경우에선<br/>
`InstrumentedHashSet`가 **<u>제공해야할 오퍼레이션</u>들을 모두 가지고 있으며**<br/>
**부모 클래스가 <u>실체화**</u>한 `Set`인터페이스을 활용하여 <br/>
부모 클래스와의 직접적인 상속을 피한다.

이가 가능한 이유는<br/>
" **부모 클래스**인 `HashSet`클래스가 `Set`인터페이스을 실체화했다 "는 것과<br/>
" `Set`인터페이스에 `InstrumentedHashSet`가 제공해야할 오퍼레이션들이 **이미 정의** "되어 있기 때문이다.

```java
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.Spliterator;

public class InstrumentedHashSet<E> implements Set<E> {
  private int addCount = 0;
  private Set<E> set;

  public InstrumentedHashSet(Set<E> set) {
    this.set = set;
  }


  @Override
  public boolean add(E e) {
    addCount++;
    return set.add(e);
  }

  @Override
  public boolean addAll(Collection<? extends E> c) {
    addCount += c.size();
    return set.addAll(c);
  }

  @Override
  public boolean remove(Object o) {
    return set.remove(o);
  }
  
  public int getAddCount() {
      return addCount;
  }

  @Override
  public void clear() {
    set.clear();
  }
  @Override
  public boolean equals(Object obj) {
    return set.equals(obj);
  }

  @Override
  public int hashCode() {
    return set.hashCode();
  }

  @Override
  public Spliterator<E> spliterator() {
    // 기존의 Set.super를 인스턴스 변수 set으로 설정함으로서 상속 회피
    return set.spliterator();
  }

  @Override
  public int size() {
    return set.size();
  }

  @Override
  public boolean isEmpty() {
    return set.isEmpty();
  }

  @Override
  public boolean contains(Object o) {
    return set.contains(o);
  }

  @Override
  public Iterator<E> iterator() {
    return set.iterator();
  }

  @Override
  public Object[] toArray() {
    return set.toArray();
  }

  @Override
  public <T> T[] toArray(T[] a) {
    return set.toArray();
  }

  @Override
  public boolean containsAll(Collection<?> c) {
    return set.containsAll(c);
  }


  @Override
  public boolean retainAll(Collection<?> c) {
    return set.retainAll(c);
  }

  @Override
  public boolean removeAll(Collection<?> c) {
    return set.removeAll(c);
  }


}
```

모두 `super`가 아닌 인스턴스 변수 `set`을 활용하여<br/>
HashSet에 대한 구현 결합도는 제거하면서<br/>
HashSet 인스턴스에게 동일한 메서드 호출을 그대로 전달하여<br/>
퍼블릭 인터페이스도 그대로 유지할 수 있게 된다.

이와 같이<br/>
<u>**인터페이스의 오퍼레이션**을 오버라이딩한 **인스턴스 메서드**</u>에서 <br/>
<u>내부의 부모 클래스 인스턴스에게</u> **동일 메서드 호출**을 <u>그대로 **전달**하는 행위</u>을<br/>
" **포워딩**(forwarding) "이라 하며

동일한 메서드를 호출하기 위해 **추가된 메서드**를<br/>
" **포워딩 메서드**(forwarding method) "라고 한다.

<br/>

### 동시 수정

어떠한 경우에는<br/>
위 방법들을 통해 합성으로 변경하더라도<br/>
동시 수정해야하는 문제가 해결되지 않을 수 있지만<br/>
그럼에도 최대한 합성으로 전환하는 것이 좋다.

<u>동시 수정을 피하지 못하더라도</u><br/>
상속보다는 **내부 캡슐화**를 통해 **합성**으로 **변환**하여 사용하는 것이 <br/>
<u>향후 발생할 내부 구현 변경에 대한 파급효과를 최소화</u>할 수 있다는 점에서<br/>
훨씬 효율적이라는 사실을 잊지말아야 한다.

> #### ⁜ 몽키 패치 (Monkey Patch)
> " <u>현재 실행 중인 환경에만 영향을 미치도록</u> **지역적**으로 코드를 **수정**하거나 **확장**하는 것 "
> 
> `루비`와 같은 동적 타입 언어에서는<br/>
> 이미 완성된 클래스에도 기능을 추가할 수 있는 "**열린 클래스**(Open Class)"라는 개념을 제공하며<br/>
> 더불어 `C#` 언어의 "**확장 메서드**", `스칼라` 언어의 "**암시적 변환**" 기능을 제공하는데<br/>
> 이들 모두 몽키 패치의 일종이다.
> 
> `Java`의 경우, 언어 차원에서의 _몽키 패치가 지원되지 않기 때문에_<br/>
> "<u>**바이트 코드**를 직접 변환</u>"하거나<br/>
> "<u>**AOP**(Aspect-Oriented Programming)</u>"을 활용하여<br/>
> 몽키 패치를 구현할 수 있다.

<br/>

---
## 상속으로 인한 폭발적인 조합 증가
상속으로 인해 결합도가 높아질 경우,<br/>
코드를 수정하는 데 필요한 작업의 양이 과도하게 늘어나는 경향이 있다.

보통 "<u>작은 기능들을 **조합**</u>하여 **큰 기능**을 수행"하는 객체를 만들 때<br/>
더 심각해진다.

- <u>하나의 기능을 추가하거나 수정</u>하기 위해 불필요하게 **많은 수의 클래스를 추가/수정**해야 함.
- "<u>단일 상속만 지원</u>하는 언어"에서는 상속으로 인해 **오히려 중복 코드의 양이 증가**

부모 클래스의 메서드를 재사용하기 위해 `super` 호출을 사용하면<br/>
원하는 결과를 쉽게 얻을지라도<br/>
자식 클래스의 부모 클래스 사이의 결합도가 높아진다.

### 중복 코드의 덫
**상속 관계**에서 결합도를 낮추기 위해서는 활용할 수 있는 방법은 꽤 존재한다.<br/>
하지만 자칫하면 중복 코드의 덫에 걸릴 수 있다는 점을 알아야 한다.

> ✅ **Case**. 부모 클래스에서 **추상 메서드** 제공

자식 클래스가 부모 클래스의 <u>이미 구현된 메서드를 호출하지 못하도록</u><br/>
부모 클래스에 "**추상 메서드**"를 제공하는 것이다.

부모 클래스가 자신이 정의한 추상 메서드를 호출하고<br/>
자식 클래스가 이 메서드를 **오버라이딩**해서<br/>
<u>부모 클래스가 원하는 로직을 제공하는 구조</u>로 바꾸게 되면<br/>
부모 클래스와 자식 클래스 사이의 결합도를 느슨하게 만들 수 있다.

하지만 문제가 발생한다.
- 모든 자식 클래스들이 <u>추상 메서드를 사용하지 않더라도</u> **무조건 오버라이딩**해야 한다.

물론 자식 클래스가 적다면 괜찮을 수 있지만<br/>
<u>자식 클래스가 많을 경우</u> 꽤나 번거로워진다.

만약 `(자식 B의 자식) → (자식 B) → [부모] ← (자식 A) ← (자식 A의 자식)`의 구조로 상속관계가 맺어질 경우,<br/>
자칫하면 `(자식 A의 자식)`와`(자식 B의 자식)`는 부모 클래스의 이름이 다른 것 외에는<br/>
**대부분의 코드가 거의 동일할 확률**이 존재한다.

자바와 같은 <u>**다중 상속이 금지**된 객체 지향 언어</u>들의 고질적인 문제이다.

위와 같이 추상 메서드를 가진 부모 클래스에 대한 상속을 활용할 경우,<br/>
<u>새로운 조합을 추가</u>하게 되면<br/>
그에 맞는 **조합 가능한 부가 기능들의 수만큼** <u>새로운 클래스를 추가</u>해야 한다.<br/>
즉, **필요할 때마다 새로운 상속 클래스들을 만들어나가야 한다**는 것이다.

더 나아가<br/>
<u>기능을 수정할 때</u>는 관련 코드가 **여러 클래스 안에 중복**되어 있기 때문에<br/>
<u>관련된 모든 클래스를 뒤져</u> **동일한 방식으로 수정**해야 한다.<br/>
( _하나라도 누락하면 오류 발생이 거의 확실해진다._ )

<br/>

이처럼 상속 남용으로 인해<br/>
<u>하나의 기능 추가를 위해 **필요 이상의 많은 클래스가 추가**되야 하는 경우</u>를<br/>
" **클래스 폭발**(Class Explosion) "문제 / " **조합의 폭발**(Combinational Explosion) "문제라 부른다.

자식 클래스가 부모 클래스의 구현에 강하게 결합되도록 강요하는 <u>"상속"의 근본적 한계</u>로 인해 발생하는 문제이다.

컴파일 시점에 결정된 관계는 변경이 불가능하기 때문에<br/>
다양한 조합이 필요한 상황에서 <br/>
<u>필요한 조합만큼 새로운 클래스를 추가하는 것</u>이 **유일한 해결 방법**인 것이다.

<br/>
<br/>

---
## 합성 관계로 변경
위와 같은 **상속의 한계**를 **해결**하기 위해선 <u>상속을 포기하는 것</u>이 방법이다.<br/>
바로 **합성 관계**로 구성하는 것이다.

- **상속** 관계 : **컴파일 타임** 결정 관계
  - 조합의 결과를 개별 클래스 안으로 밀어넣는 방법
- **합성** 관계 : **런 타임** 결정 관계
  - 조합을 구성하는 요소들을 개별 클래스로 구현한 후, 실행 시점에 인스턴스를 조립하는 방법

구현 시점에 정책들의 관계를 고정시킬 필요가 없으며<br/>
실행 시점에 정책들의 관계를 유연하게 변경할 수 있게 되면서<br/>
컴파일 의존성에 속박되지 않고 다양한 방식의 런타임 의존성을 구성할 수 있다는 장점이 있다.<br/>
(단, 컴파일 타임 의존성과 런타임 의존성의 거리가 멀어지면서 설계의 복잡도는 높아진다.)

새로운 조합이 필요할 때마다<br/>
<u>오직 필요한 하나의 클래스만 추가</u>하기만 하면 <br/>
**런 타임에 필요한 구현체를 조합**해서 원하는 기능을 얻을 수 있다.

또한 요구사항이 변경된다 하더라도 <u>오직 하나의 클래스만 수정하면 된다</u>는 편리성이 있다.<br/>
("**단일 책임 원칙**" 준수)

### "객체 합성"이 클래스 상속보다 더 좋은 방법이다.
<u>**구현**을 재사용</u>하는 "**상속**"에 비해<br/>
<u>**객체의 인터페이스**를 재사용</u>하는 "**합성**"이 <br/>
더욱 깔끔하게 코드를 재사용하면서 결합도를 유지하는 방법이다.

<br/>
<br/>

## 믹스인(Mixin)
> 상속과 합성의 특성을 모두 보유하는 형식
 
"**싱속**" <br/>
→ _간편한 다른 클래스 구현 재사용 & 점진적 확장 (장점)_<br/>
→ _부모 클래스 - 자식클래스 강한 결합 & 취약한 수정 및 확장 설계 (단점)_ 

이에 반해 합성은 구체적인 구현이 아닌<br/>
추상적인 인터페이스에 의존하기 때문에 강한 결합을 피할 수 있는 것이다.

만약 상속을 사용해서 재사용해야 한다면<br/>
구체적인 코드 재사용이 가능하면서도 낮은 결합도를 유지할 수 있는 적합한 추상화가 필요하다.<br/>
→ ⌜ **믹스인(mixin)** : 객체 **생성시**, <u>**코드 일부**를 클래스 안에 섞어 넣어</u> 재사용하는 방법 ⌟ 활용

- **믹스인**(mixin) : <u>**컴파일 시점**에 필요한 **코드 조각**을 조합</u>하는 재사용 방법
  - 합성 : **실행 시점**에 객체 조합
  - 상속 : 부모 - 자식의 **개념적 결합**

간단하게 말해서 <u>**부분적** 기능</u>에 대해서만 구현된 구현체를 상속받아 재사용하는 방식이다.

<br/>

> ※ _`Flavors` 언어 : 믹스인 도입<br/>
> ☞ `Flavors` 특징 흡수 `CLOS(Common Lisp Object System)` 에 의해 대중화_
> 
> - `Scala` 언어 : `trait` 이용하여 믹스인 구현<br/>
> 
> 
> - _**Java**의 경우, <u>`Comparable` 인터페이스</u> 등이 "**믹스인 인터페이스**"라 취급<br/> : 해당 클래스끼 비교하는 기능을 혼합시키는 형식_ 

<br/>

믹스인은 **특정 클래스**의 <u>**메서드**를 재사용하고 **기능을 확장**하기 위해</u> 사용되어 왔고<br/>
해당 상속 계층 안에서 <u>확장한 클래스보다 더 **하위**</u>에 위치하게 된다.<br/>
(대상 클래스의 <u>자식 클래스처럼 사용</u>되는 용도 → "**추상 서브클래스**(**Abstract Subclass**)"라 불리기도 함.)

또한 믹스인을 사용하면<br/>
특정 클래스에 대한 **변경/확장**을 <u>독립적으로 구현</u>한 후,<br/>
**"<u>필요한 시점</u>"에 <u>차례대로 추가**</u>할 수 있다.<br/>
( → "**쌓을 수 있는 변경**(**Stackable Modification**)" )