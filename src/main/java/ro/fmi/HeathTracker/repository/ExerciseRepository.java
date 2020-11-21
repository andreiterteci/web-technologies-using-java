package ro.fmi.HeathTracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.fmi.HeathTracker.domain.Exercise;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, String> {
}
