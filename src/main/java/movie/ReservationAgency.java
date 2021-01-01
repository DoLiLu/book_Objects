package movie;

import java.time.LocalDateTime;

public class ReservationAgency {
    public boolean isDiscountable(DiscountCondition condition, Screening screening){
        if(condition.getType() == DiscountConditionType.PERIOD)
            return isSatisfiedByPeriod(condition, screening);

        return isSatisfiedBySequence(condition, screening);

    }

    private boolean isSatisfiedByPeriod(DiscountCondition condition, Screening screening) {
        return screening.getWhenScreened().getDayOfWeek().equals(condition.getDayOfWeek()) &&
                condition.getStartTime().compareTo(screening.getWhenScreened().toLocalTime()) <= 0 &&
                condition.getEndTime().compareTo(screening.getWhenScreened().toLocalTime()) >= 0;
    }

    private boolean isSatisfiedBySequence(DiscountCondition condition, Screening screening) {
        return condition.getSequence() == screening.getSequence();
    }

    private Reservation reserve(Screening screening, Customer customer, int audienceCount){
        Money fee = screening.calculateFee(audienceCount);
        return new Reservation(customer, screening, fee, audienceCount);
    }

    private boolean checkDiscountable(Screening screening){
        return screening.getMovie().getDiscountConditions().stream()
                .anyMatch(condition -> condition.isDiscountable(screening));
    }
}
