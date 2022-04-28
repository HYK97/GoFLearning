package create.builder.after;

import create.builder.before.TourPlan;

import java.time.LocalDate;

public class TourDirector {
    private TourPlanBuilder tourPlanBuilder;

    public TourDirector(TourPlanBuilder tourPlanBuilder) {
        this.tourPlanBuilder = tourPlanBuilder;
    }

    public TourPlan seoulTrip() {
        return tourPlanBuilder.newInstance().title("서울여행")
                .nightsAndDays(2, 3)
                .startDate(LocalDate.of(2022, 12, 3))
                .whereToStay("민박")
                .addPlan(0, "체크인하기")
                .addPlan(1, "인천가기")
                .getPlan();
    }
}
