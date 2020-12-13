package ro.fmi.HeathTracker.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.fmi.HeathTracker.domain.enums.ExerciseType;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ExerciseModel {

    private String id;

    private String name;

    private ExerciseType exerciseType;

    private Long duration;
}
