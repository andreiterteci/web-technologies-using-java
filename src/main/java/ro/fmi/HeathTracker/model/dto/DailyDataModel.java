package ro.fmi.HeathTracker.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DailyDataModel {
    private String id;

    private LocalDate date;

    private List<FitnessModel> fitness;

    private List<MealModel> meals;
}
