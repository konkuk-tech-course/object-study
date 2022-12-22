package domain.policy.discount;

import domain.Money;
import domain.Screening;

public class NoneDiscountPolicy implements DiscountPolicy {
//    할인이 없는 경우 Condition의 List가 필요없기 때문에
//    클래스 상속이 아닌 인터페이스로 구현
//    @Override
//    protected Money getDiscountAmount(Screening screening) {
//        return Money.ZERO;
//    }

    @Override
    public Money calculateDiscountAmount(Screening screening) {
        return Money.ZERO;
    }
}
