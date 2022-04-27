package builder.after;

import builder.before.TourPlan;

import java.time.LocalDate;

public interface TourPlanBuilder {

    DefaultTourBuilder newInstance();

    TourPlanBuilder nightsAndDays(int nights, int days);

    TourPlanBuilder title(String title);

    TourPlanBuilder startDate(LocalDate localDate);

    TourPlanBuilder whereToStay(String whereToStay);

    TourPlanBuilder addPlan(int day, String plan);

    TourPlan getPlan();
}