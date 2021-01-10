package ro.fmi.HeathTracker.mapper;

import ro.fmi.HeathTracker.domain.Exercise;
import ro.fmi.HeathTracker.domain.Fitness;
import ro.fmi.HeathTracker.domain.Meal;
import ro.fmi.HeathTracker.model.dto.ExerciseModel;
import ro.fmi.HeathTracker.model.dto.FitnessModel;
import ro.fmi.HeathTracker.model.dto.MealModel;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class FitnessMapper {

    private FitnessMapper() {
    }

    public static FitnessModel toModel(final Fitness fitness) {
        final List<ExerciseModel> exerciseModels = fitness.getExercise()
                .stream()
                .map(FitnessMapper::toExerciseModel)
                .collect(Collectors.toList());

        return FitnessModel.builder()
                .id(fitness.getId())
                .exercises(exerciseModels)
                .duration(fitness.getDuration())
                .date(fitness.getDailyData().getDate())
                .build();
    }

    public static Fitness toEntity(final FitnessModel fitness) {
        final List<Exercise> exercises = fitness.getExercises()
                .stream()
                .map(FitnessMapper::toExercise)
                .collect(Collectors.toList());

        return Fitness.builder()
                .id(fitness.getId())
                .exercise(exercises)
                .duration(fitness.getDuration())
                .build();
    }

    public static ExerciseModel toExerciseModel(final Exercise exercise) {
        return ExerciseModel.builder()
                .duration(exercise.getDuration())
                .exerciseType(exercise.getExerciseType())
                .name(exercise.getName())
                .id(exercise.getId())
                .build();
    }

    public static Exercise toExercise(final ExerciseModel exercise) {
        return Exercise.builder()
                .duration(exercise.getDuration())
                .exerciseType(exercise.getExerciseType())
                .name(exercise.getName())
                .id(exercise.getId())
                .build();
    }
}
