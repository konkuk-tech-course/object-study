package domain.policy.discount;

import domain.Money;
import domain.Screening;
import domain.policy.discount.util.DiscountCondition;

import java.util.List;

public class AmountDiscountPolicy extends DefaultDiscountPolicy {
    private Money discountAmount ;

    public AmountDiscountPolicy(List<DiscountCondition> conditions, Money discountAmount) {
        super(conditions);
        this.discountAmount = discountAmount;
    }

    @Override
    protected Money getDiscountAmount(Screening screening) {
        return discountAmount;
    }
}
