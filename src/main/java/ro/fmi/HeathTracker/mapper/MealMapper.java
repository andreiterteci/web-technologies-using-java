package ro.fmi.HeathTracker.mapper;

import ro.fmi.HeathTracker.domain.Meal;
import ro.fmi.HeathTracker.model.dto.MealModel;

import java.time.LocalDate;

public class MealMapper {

    private MealMapper() {
    }

    public static MealModel toModel(final Meal meal, final LocalDate date){
        return MealModel.builder()
                .id(meal.getId())
                .calories(meal.getCalories())
                .names(meal.getNames())
                .date(date)
                .build();
    }
}
