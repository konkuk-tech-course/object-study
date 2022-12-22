package domain;

import domain.policy.discount.DiscountPolicy;

import java.time.Duration;

public class Movie {
    private String title;
    private Duration runningTime;
    private Money fee ;
    private DiscountPolicy discountPolicy; // 인터페이스를 상속 -> NoneDiscountPolicy까지 동일하게 처리 가능

    public Movie(String title, Duration runningTime, Money fee, DiscountPolicy discountPolicy) {
        this.title = title;
        this.runningTime = runningTime;
        this.fee = fee;
        this.discountPolicy = discountPolicy;
    }

    public Money getFee(){
        return fee ;
    }
    public Money calculateMovieFee(Screening screening){
//        if(Objects.isNull(discountPolicy)){
//            return fee;
//        }
        // NoneDiscountPolicy로 별도 처리 필요 없
        return fee.minus(discountPolicy.calculateDiscountAmount(screening));
    }
}
