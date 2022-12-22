package org.example;

import java.util.List;

public class PercentDiscountPolicy extends DefaultDiscountPolicy{

    private double percent;

    public PercentDiscountPolicy(double percent, DiscountCondition... conditions) {
        super(List.of(conditions));
        this.percent = percent;
    }


    @Override
    protected Money getDiscountAmount(Screening screening) {
        return screening.getMovieFee().times(percent);
    }
}
