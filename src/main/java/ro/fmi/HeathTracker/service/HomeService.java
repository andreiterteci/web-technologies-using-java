package ro.fmi.HeathTracker.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.fmi.HeathTracker.domain.DailyData;
import ro.fmi.HeathTracker.domain.Meal;
import ro.fmi.HeathTracker.domain.User;
import ro.fmi.HeathTracker.domain.enums.Gender;
import ro.fmi.HeathTracker.domain.enums.Recommendation;
import ro.fmi.HeathTracker.exception.UserNotFoundException;
import ro.fmi.HeathTracker.model.dto.HealthModel;
import ro.fmi.HeathTracker.repository.DailyDataRepository;
import ro.fmi.HeathTracker.repository.UserRepository;
import ro.fmi.HeathTracker.util.PrincipalUtil;

import javax.transaction.Transactional;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class HomeService {

    private final DailyDataRepository dailyDataRepository;
    private final UserRepository userRepository;

    public HealthModel calculateUserScore() {
        final User user = userRepository.findByEmail(PrincipalUtil.getPrincipal()).orElseThrow(UserNotFoundException::new);
        List<DailyData> dailyData = dailyDataRepository.findAllByUser(user);
        Optional<Double> totalCaloriesBurnedByExercises = getTotalCaloriesBurnedByExercises(dailyData);
        totalCaloriesBurnedByExercises.ifPresent(aDouble -> aDouble = (aDouble + 100) * user.getWeight() / 100);
        Optional<Long> totalCalories = getTotalCalories(dailyData);
        Integer totalDays = getTotalDays(dailyData);
        Double heathScore = calculateHealthScore(user, totalCaloriesBurnedByExercises, totalCalories, totalDays);
        List<Recommendation> recommendations = getRecommendations(user, totalCaloriesBurnedByExercises, totalCalories, totalDays, heathScore);
        DecimalFormat df2 = new DecimalFormat("#.##");
        df2.setRoundingMode(RoundingMode.DOWN);
        return HealthModel.builder()
                .healthScore(df2.format(heathScore))
                .healthScoreDecimal(heathScore)
                .recommendations(recommendations)
                .build();
    }

    private List<Recommendation> getRecommendations(User user,
                                                    Optional<Double> totalCaloriesBurnedByExercises,
                                                    Optional<Long> totalCalories,
                                                    Integer totalDays,
                                                    Double heathScore) {
        List<Recommendation> recommendations = new ArrayList<>();
        if (totalCaloriesBurnedByExercises.isPresent()) {
            if (totalCaloriesBurnedByExercises.get() / totalDays < 1000) {
                recommendations.add(Recommendation.EXERCISE_NOT_BURN_ENOUGH);
            } else if (totalCaloriesBurnedByExercises.get() / totalDays > 1000) {
                recommendations.add(Recommendation.EXERCISE_BURN_TOO_MUCH);
            }
        } else {
            recommendations.add(Recommendation.NO_EXERCISE);
        }
        if (totalCalories.isPresent()) {
            if (user.getGender().equals(Gender.MALE)) {
                if (totalCalories.get() / totalDays > 3000) {
                    recommendations.add(Recommendation.TOO_MUCH_CALORIES);
                } else if (totalCalories.get() / totalDays < 2000) {
                    recommendations.add(Recommendation.NOT_ENOUGH_CALORIES);
                }
            } else {
                if (totalCalories.get() / totalDays > 2500) {
                    recommendations.add(Recommendation.TOO_MUCH_CALORIES);
                } else if (totalCalories.get() / totalDays < 1500) {
                    recommendations.add(Recommendation.NOT_ENOUGH_CALORIES);
                }
            }
        }
        if (heathScore.equals(100D)) {
            recommendations.add(Recommendation.PERFECTLY_FIT);
        }
        return recommendations;
    }

    private int getTotalDays(List<DailyData> dailyData) {
        return dailyData
                .stream()
                .map(DailyData::getDate)
                .collect(Collectors.toSet()).size();
    }

    private Optional<Long> getTotalCalories(List<DailyData> dailyData) {
        return dailyData.stream()
                .map(data -> data.getMeals()
                        .stream()
                        .map(Meal::getCalories)
                        .collect(Collectors.toList()))
                .flatMap(Collection::stream)
                .reduce(Long::sum);
    }

    private Double calculateHealthScore(User user,
                                        Optional<Double> totalCaloriesBurnedByExercises,
                                        Optional<Long> totalCalories,
                                        Integer totalDays) {
        Double healthScore = 0D;
        if (totalCalories.isPresent()) {
            Double averageCaloriesPerDay = totalCaloriesBurnedByExercises
                    .map(aDouble -> (totalCalories.get() - aDouble) / totalDays)
                    .orElseGet(() -> (double) ((totalCalories.get()) / totalDays));
            if (user.getGender().equals(Gender.MALE)) {
                healthScore = averageCaloriesPerDay * 100 / 2500;
            }
            if (user.getGender().equals(Gender.FEMALE)) {
                healthScore = averageCaloriesPerDay * 100 / 2000;
            }
            if (healthScore > 100 && healthScore <= 200) {
                healthScore = 200 - healthScore;
            } else if (healthScore > 200) {
                healthScore = 0D;
            }
        }
        return healthScore;
    }

    private Optional<Double> getTotalCaloriesBurnedByExercises(List<DailyData> dailyData) {
        return dailyData.stream()
                .map(data -> data.getFitness()
                        .stream()
                        .map(fitness -> fitness.getExercise()
                                .stream()
                                .map(exercise -> exercise.getDuration() * exercise.getExerciseType().getCaloriesBurned())
                                .collect(Collectors.toList())
                        )
                        .flatMap(Collection::stream)
                        .collect(Collectors.toList())
                )
                .flatMap(Collection::stream)
                .reduce(Double::sum);
    }
}
