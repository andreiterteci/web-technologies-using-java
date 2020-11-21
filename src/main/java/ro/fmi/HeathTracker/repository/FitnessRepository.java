package ro.fmi.HeathTracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.fmi.HeathTracker.domain.Fitness;

@Repository
public interface FitnessRepository extends JpaRepository<Fitness, String> {
}
