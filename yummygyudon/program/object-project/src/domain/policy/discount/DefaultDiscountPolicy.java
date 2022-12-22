package domain.policy.discount;

import domain.Money;
import domain.Screening;
import domain.policy.discount.util.DiscountCondition;

import java.util.List;

public abstract class DefaultDiscountPolicy implements DiscountPolicy{
    private List<DiscountCondition> conditions ;

    public DefaultDiscountPolicy(List<DiscountCondition> conditions){
        this.conditions = conditions;
    }

    // 인터페이스를 상속받는 방식으로 변환하여 Override 하게끔한다.
    @Override
    public Money calculateDiscountAmount(Screening screening) {
        for(DiscountCondition condition : conditions){
            if(condition.isSatisfiedBy(screening)){
                return getDiscountAmount(screening);
            }
        }
        return Money.ZERO;
    }

    abstract protected Money getDiscountAmount(Screening screening);
}
