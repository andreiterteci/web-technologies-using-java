package ro.fmi.HeathTracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.fmi.HeathTracker.domain.Meal;

@Repository
public interface MealRepository extends JpaRepository<Meal, String> {
}
