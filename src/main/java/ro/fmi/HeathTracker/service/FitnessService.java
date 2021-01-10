package ro.fmi.HeathTracker.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.fmi.HeathTracker.domain.DailyData;
import ro.fmi.HeathTracker.domain.Fitness;
import ro.fmi.HeathTracker.domain.User;
import ro.fmi.HeathTracker.exception.FitnessNotFoundException;
import ro.fmi.HeathTracker.exception.UserNotFoundException;
import ro.fmi.HeathTracker.mapper.FitnessMapper;
import ro.fmi.HeathTracker.model.dto.ExerciseModel;
import ro.fmi.HeathTracker.model.dto.FitnessModel;
import ro.fmi.HeathTracker.repository.DailyDataRepository;
import ro.fmi.HeathTracker.repository.ExerciseRepository;
import ro.fmi.HeathTracker.repository.FitnessRepository;
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
public class FitnessService {

    private final UserRepository userRepository;
    private final DailyDataRepository dailyDataRepository;
    private final FitnessRepository fitnessRepository;

    public List<FitnessModel> getAllByUser() {
        final User user = userRepository.findByEmail(PrincipalUtil.getPrincipal()).orElseThrow(UserNotFoundException::new);
        return user.getDailyData()
                .stream()
                .map(data -> data.getFitness()
                        .stream()
                        .map(FitnessMapper::toModel)
                        .collect(Collectors.toList()))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    public void create(final FitnessModel fitnessModel) {
        final User user = userRepository.findByEmail(PrincipalUtil.getPrincipal()).orElseThrow(UserNotFoundException::new);
        Optional<DailyData> optionalDailyData = dailyDataRepository.findByDateAndUser(LocalDate.now(), user);
        Long duration = fitnessModel.getExercises().stream().map(ExerciseModel::getDuration).reduce((long) 0, Long::sum);
        fitnessModel.setDuration(duration);
        if (!optionalDailyData.isPresent()) {
            DailyData dailyData = new DailyData();
            dailyData.setUser(user);
            dailyData.setDate(LocalDate.now());
            Fitness fitnessToBeSaved = FitnessMapper.toEntity(fitnessModel);
            fitnessToBeSaved.setDailyData(dailyData);
            dailyData.setFitness(Collections.singletonList(fitnessToBeSaved));
            dailyDataRepository.save(dailyData);
        } else {
            DailyData dailyData = optionalDailyData.get();
            Fitness fitnessToBeSaved = FitnessMapper.toEntity(fitnessModel);
            fitnessToBeSaved.setDailyData(dailyData);
            fitnessToBeSaved.getExercise().forEach(exercise -> {
                exercise.setFitness(fitnessToBeSaved);
            });
            List<Fitness> fitnesses = dailyData.getFitness();
            fitnesses.add(fitnessToBeSaved);
            dailyData.setFitness(fitnesses);
            dailyDataRepository.save(dailyData);
        }
    }

    public FitnessModel getById(final String id) {
            return fitnessRepository.findById(id)
                    .map(FitnessMapper::toModel)
                    .orElseThrow(FitnessNotFoundException::new);
    }

    public void delete(final String id) {
        Fitness fitness = fitnessRepository.findById(id).orElseThrow(FitnessNotFoundException::new);
        fitnessRepository.delete(fitness);
    }

    public void update(FitnessModel fitnessModel) {
        Fitness fitness = fitnessRepository.findById(fitnessModel.getId()).orElseThrow(FitnessNotFoundException::new);
        Fitness updated = FitnessMapper.toEntity(fitnessModel);
        updated.setDailyData(fitness.getDailyData());
        fitnessRepository.save(updated);
    }
}
