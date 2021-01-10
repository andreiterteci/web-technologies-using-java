package ro.fmi.HeathTracker.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.fmi.HeathTracker.domain.DailyData;
import ro.fmi.HeathTracker.domain.Meal;
import ro.fmi.HeathTracker.domain.User;
import ro.fmi.HeathTracker.exception.MealNotFoundException;
import ro.fmi.HeathTracker.exception.UserNotFoundException;
import ro.fmi.HeathTracker.mapper.MealMapper;
import ro.fmi.HeathTracker.model.dto.MealModel;
import ro.fmi.HeathTracker.repository.DailyDataRepository;
import ro.fmi.HeathTracker.repository.MealRepository;
import ro.fmi.HeathTracker.repository.UserRepository;
import ro.fmi.HeathTracker.util.PrincipalUtil;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MealService {

    private final MealRepository mealRepository;

    private final UserRepository userRepository;

    private final DailyDataRepository dailyDataRepository;

    public List<MealModel> getAllByUser() {
        final User user = userRepository.findByEmail(PrincipalUtil.getPrincipal()).orElseThrow(UserNotFoundException::new);
        return user.getDailyData()
                .stream()
                .map(data -> data.getMeals()
                        .stream()
                        .map(MealMapper::toModel)
                        .collect(Collectors.toList()))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    public void create(final MealModel mealModel) {
        final User user = userRepository.findByEmail(PrincipalUtil.getPrincipal()).orElseThrow(UserNotFoundException::new);
        Optional<DailyData> optionalDailyData = dailyDataRepository.findByDateAndUser(LocalDate.now(), user);
        if (!optionalDailyData.isPresent()) {
            DailyData dailyData = new DailyData();
            dailyData.setUser(user);
            dailyData.setDate(LocalDate.now());
            Meal mealToBeSaved = MealMapper.toEntity(mealModel);
            mealToBeSaved.setDailyData(dailyData);
            dailyData.setMeals(Collections.singletonList(mealToBeSaved));
            dailyDataRepository.save(dailyData);
        } else {
            DailyData dailyData = optionalDailyData.get();
            Meal mealToBeSaved = MealMapper.toEntity(mealModel);
            mealToBeSaved.setDailyData(dailyData);
            List<Meal> meals = dailyData.getMeals();
            meals.add(mealToBeSaved);
            dailyData.setMeals(meals);
            dailyDataRepository.save(dailyData);
        }

    }

    public MealModel getById(final String id) {
        return mealRepository.findById(id)
                .map(MealMapper::toModel)
                .orElseThrow(MealNotFoundException::new);
    }

    public void update(MealModel mealModel) {
        Meal meal = mealRepository.findById(mealModel.getId()).orElseThrow(MealNotFoundException::new);
        Meal updatedMeal = MealMapper.toEntity(mealModel);
        updatedMeal.setDailyData(meal.getDailyData());
        mealRepository.save(updatedMeal);
    }

    public void delete(String id) {
        Meal meal = mealRepository.findById(id).orElseThrow(MealNotFoundException::new);
        mealRepository.delete(meal);
    }
}
