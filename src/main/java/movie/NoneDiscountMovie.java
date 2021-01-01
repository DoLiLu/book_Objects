package movie;

import java.time.Duration;

public class NoneDiscountMovie extends Movie{

    public NoneDiscountMovie(String title, Duration runningTime, Money fee, double percent, DiscountCondition... discountConditions){
        super(title, runningTime, fee, discountConditions);
    }

    @Override
    protected Money calculateDiscountAmount() {
        return Money.ZERO;
    }
}
