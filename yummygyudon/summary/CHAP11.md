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

## 합성 관계로 변경

## 믹스인