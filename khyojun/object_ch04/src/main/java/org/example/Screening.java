package org.example;

import java.time.LocalDateTime;

public class Screening {

    private Movie movie;
    private int sequence;
    private LocalDateTime whenScreened;



    public Screening(Movie movie, int sequence, LocalDateTime whenScreened) {
        this.movie = movie;
        this.sequence = sequence;
        this.whenScreened = whenScreened;
    }


    public Money calculateFee(int audienceCount){
        switch (movie.getMovieType()){
            case AMOUNT_DISCOUNT:
                if(movie.isDiscountable(whenScreened,sequence)){
                    return movie.calculateAmountDiscountFee().times(audienceCount);
                }
                break;
            case PERCENT_DISCOUNT:
                if(movie.isDiscountable(whenScreened,sequence)){
                    return movie.calculatePercentDiscountFee().times(audienceCount);
                }
                break;
            case NONE_DISCOUNT:
                return movie.calculateNoDiscountFee().times(audienceCount);
        }
        return movie.calculateNoDiscountFee().times(audienceCount);
    }


}
