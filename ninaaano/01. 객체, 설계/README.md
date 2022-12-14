밀접하게 연관된 작업만을 수행하고 연관성 없는 작업은 다른 객체에게 위임하는 객체를 **응집도** 가 높다고 한다
자신의 데이터를 스스로 처리하는 자율적인 객체를 만들면 결합도를 낮추고 응집도를 높일 수 있다

객체의 응집도를 높이려면 객체 스스로 자신의 데이터를 책임져야한다
객체는 자신의 데이터를 스스로 처리하는 자율적인 존재여야한다

### 절차지향과 객체지향

절차적 프로그래밍 : 프로세스와 데이터를 별도의 모듈에 위치시키는 방식
절차적 프로그래밍은 우리의 직관에 위배된다. 데이터 변경으로 인한 영향을 지역적으로 고립시키기 어렵다.  한 클래스에서 내부 메소드를 변경하려면 다른 클래스의 메소드도 함께 변경해야 한다. 절차적 프로그래밍은 프로세스가 필요한 모든 데이터에 의존해야 한다는 근본적인 문제점 때문에 변경에 취약하다

객체지향 프로그래밍 : 데이터와 프로세스가 동일한 모듈 내부에 위치허도록프로그래밍 하는 방식
훌륭한 객체지향 설계의 핵심은 캡슐화를 이용해 의존성을 적절히 관리함으로써 객체 사이의 결합도를 낮춘다. 그래서 절차지향에 비해 변경에 유연하다. 자신의 문제는 스스로 처리하기 때문에 객체 내부의 변경이 객체 외부에 파급되지 않도록 제어할 수 있기 때문에 변경하기 수월하다.

한 곳에 몰려있던 책임이 개별 객체로 이동하는 것을 **책임의 이동** 이라고 한다.
객체지향에서는 책임이 적절히 분배가 되어 객체는 자신을 스스로 책임진다.

코드에서 데이터와 데이터를 사용하는 프로세스가 별도의 객체에 위치하고 있다면 절차적 프로그래밍 방식일 확률이 높다

데이터와 데이터를 사용하는 프로세스가 동일한 객체 안에 위치한다면 객체지향 프로그래밍 방식을 따르고 있을 확률이 높다

객체지향 설계의 핵심은 저걸한 객체에 적절한 책임을 할당하는 것이다

설계를 어렵게 만드는 것은 의존성이다.
불필요한 의존성을 제거함으로써 객체 사이에 결합도를 낮추는 것이다. 결합도를 낮추기 위한 방법은 몰라도 되는 정보는 내부로 감춰 캡슐화하는 것이다. 결과적으로 불필요한 세부사항을 객체 내부로 캡슐화하는 것은 객체의 자율성을 높이고 응집도 높은 객체들의 공동체를 창조할 수 있게한다.

능동적이고 자율적인 존재로 소프트웨어 객체를 설계하는 원칙을 가리켜 의인화 라고 부른다

훌륭한 객체지향 설계

- 낮은 결합도와 높은 응집도를 가지고 협력하도록 최소한의 의존성만 남기는 것
- 협력하는 객체 사이의 의존성을 적절하게 관리하는 설계
- 소프트웨어를 구성하는 모든 객체들이 자율적으로 행동하는 설계

변경을 수용할 수 있는 설계가 중요한 이유는 요구사항히 항상 변경되기 때문이다. 협력하는 객체들 사이의 의존성을 적절하게 조절함으로써 변경에 용이한 설계를 만드는 것