package ro.fmi.HeathTracker.domain.enums;

public enum ExerciseType {
    LIFTING(3.60),
    AEROBICS(4.96),
    YOGA(4.96),
    CIRCUIT_TRAINING(9.93),
    BICYCLING(13.03),
    HANDBALL(14.86),
    SWIMMING(13.63),
    SOCCER(9.93),
    TENNIS(8.66),
    BASKETBALL(8.06);

    Double caloriesBurnedInAMinute;

    ExerciseType(Double caloriesBurnedInAMinute){
        this.caloriesBurnedInAMinute = caloriesBurnedInAMinute;
    }

    public Double getCaloriesBurned(){
        return caloriesBurnedInAMinute;
    }
}
