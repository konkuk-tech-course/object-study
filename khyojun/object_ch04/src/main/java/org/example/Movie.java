package org.example;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class Movie {
    private String title;
    private Duration duration;
    private Money fee;
    private List<DiscountCondition> discountConditionList;

    private MovieType movieType;
    private Money discountAmount;
    private double discountPercent;


    public boolean isDiscountable(LocalDateTime whenScreened, int sequence){
        for(DiscountCondition condition : discountConditionList){
            if(condition.getType() == DiscountConditionType.PERIOD){
                if(condition.isDiscountable(whenScreened.getDayOfWeek(), whenScreened.toLocalTime())){
                    return true;
                }
            }
            else{
                if(condition.isDiscountable(sequence)){
                    return true;
                }
            }
        }
        return false;
    }


    public MovieType getMovieType() {
        return movieType;
    }

    public Money calculateAmountDiscountFee(){
        if(movieType!=MovieType.AMOUNT_DISCOUNT){
            throw new IllegalArgumentException();
        }
        return fee.minus(discountAmount);
    }

    public Money calculatePercentDiscountFee(){
        if(movieType!=MovieType.PERCENT_DISCOUNT){
            throw new IllegalArgumentException();
        }
        return fee.minus(fee.times(discountPercent));
    }

    public Money calculateNoDiscountFee(){
        if(movieType!=MovieType.PERCENT_DISCOUNT){
            throw new IllegalArgumentException();
        }
        return fee;
    }

}
