package org.example;

import java.util.List;

public class AmountDiscountPolicy extends DefaultDiscountPolicy{

    private Money discountAmount;

    public AmountDiscountPolicy(Money discountAmount, DiscountCondition... conditions) {
        super(List.of(conditions));
        this.discountAmount = discountAmount;
    }


    @Override
    protected Money getDiscountAmount(Screening screening) {
        return discountAmount;
    }
}
