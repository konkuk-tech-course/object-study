package org.example;

import java.time.Duration;

public class Movie {
    private String title;
    private Duration duration;
    private Money fee;
    private List<DiscountCondition> discountConditionList;

    private MovieType movieType;
    private Money discountAmount;
    private double discountPercent;

    public Money getFee() {
        return fee;
    }

    public List<DiscountCondition> getDiscountConditionList() {
        return discountConditionList;
    }

    public MovieType getMovieType() {
        return movieType;
    }

    public Money getDiscountAmount() {
        return discountAmount;
    }

    public void setFee(Money fee) {
        this.fee = fee;
    }

    public void setDiscountConditionList(List<DiscountCondition> discountConditionList) {
        this.discountConditionList = discountConditionList;
    }

    public void setMovieType(MovieType movieType) {
        this.movieType = movieType;
    }

    public void setDiscountAmount(Money discountAmount) {
        this.discountAmount = discountAmount;
    }

    public void setDiscountPercent(double discountPercent) {
        this.discountPercent = discountPercent;
    }

    public double getDiscountPercent() {
        return discountPercent;
    }
}
