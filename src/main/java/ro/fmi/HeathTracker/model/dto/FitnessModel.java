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
public class FitnessModel {
    private String id;

    private List<ExerciseModel> exercises;

    private Long duration;

    private LocalDate date;
}
