package ro.fmi.HeathTracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.fmi.HeathTracker.domain.HealthData;

@Repository
public interface HealthDataRepository extends JpaRepository<HealthData, String> {
}
