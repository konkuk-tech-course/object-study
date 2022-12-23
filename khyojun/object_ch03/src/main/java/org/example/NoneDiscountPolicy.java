package org.example;

public class NoneDiscountPolicy implements DiscountPolicy{



    @Override
    public Money calculateDiscountAmount(Screening screening) {
        return Money.ZERO;
    }
}
