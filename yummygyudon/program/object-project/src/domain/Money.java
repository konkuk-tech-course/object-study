package domain;

import java.math.BigDecimal;

public class Money {
    public static final Money ZERO = Money.wons(0);

    private final BigDecimal amount;
    Money(BigDecimal amount){
        this.amount = amount;
    }

    public static Money wons(long amount){
        return new Money(BigDecimal.valueOf(amount));
    }
    public static Money wons(double amount){
        return new Money(BigDecimal.valueOf(amount));
    }

    public Money plus(Money money){
        return new Money(this.amount.add(money.amount));
    }
    public Money plus(long money) {
        return new Money(this.amount.add(BigDecimal.valueOf(money)));
    }
    public Money minus(Money money){
        return new Money(this.amount.subtract(money.amount));
    }

    public Money times(double percent){
        return new Money(this.amount.multiply(
                BigDecimal.valueOf(percent)
        ));
    }

    public boolean isLessThan(Money other){
        return amount.compareTo(other.amount) < 0 ;
    }
    public boolean isGreaterThanOrEquals(Money other){
        return amount.compareTo(other.amount) >= 0 ;
    }
}