package ro.fmi.HeathTracker.mapper;

import ro.fmi.HeathTracker.domain.Meal;
import ro.fmi.HeathTracker.model.dto.MealModel;

import java.time.LocalDate;

public class MealMapper {

    private MealMapper() {
    }

    public static MealModel toModel(final Meal meal){
        return MealModel.builder()
                .id(meal.getId())
                .calories(meal.getCalories())
                .names(meal.getNames())
                .date(meal.getDailyData().getDate())
                .build();
    }

    public static Meal toEntity(final MealModel mealModel){
        return Meal.builder()
                .id(mealModel.getId())
                .calories(mealModel.getCalories())
                .names(mealModel.getNames())
                .build();
    }
}
