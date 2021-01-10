package ro.fmi.HeathTracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.fmi.HeathTracker.domain.DailyData;
import ro.fmi.HeathTracker.domain.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface DailyDataRepository extends JpaRepository<DailyData, String> {
    Optional<DailyData> findByDateAndUser(final LocalDate date, final User user);
    List<DailyData> findAllByUser(final User user);
}
