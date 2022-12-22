package domain.policy.discount.util;

import domain.Screening;

public interface DiscountCondition {
    boolean isSatisfiedBy(Screening screening);
}
