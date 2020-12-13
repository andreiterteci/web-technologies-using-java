package ro.fmi.HeathTracker.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.fmi.HeathTracker.domain.User;
import ro.fmi.HeathTracker.exception.UserNotFoundException;
import ro.fmi.HeathTracker.mapper.MealMapper;
import ro.fmi.HeathTracker.model.dto.MealModel;
import ro.fmi.HeathTracker.repository.MealRepository;
import ro.fmi.HeathTracker.repository.UserRepository;
import ro.fmi.HeathTracker.util.PrincipalUtil;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MealService {

    private final MealRepository mealRepository;

    private final UserRepository userRepository;

    public List<MealModel> getAllByUser() {
        final User user = userRepository.findByEmail(PrincipalUtil.getPrincipal()).orElseThrow(UserNotFoundException::new);
        return user.getHealthData()
                .stream()
                .map(data -> data.getMeals()
                        .stream()
                        .map(meal -> MealMapper.toModel(meal, data.getDate()))
                        .collect(Collectors.toList()))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }
}
