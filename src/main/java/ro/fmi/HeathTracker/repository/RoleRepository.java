package ro.fmi.HeathTracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.fmi.HeathTracker.domain.Role;
import ro.fmi.HeathTracker.domain.enums.RoleType;

import java.util.Optional;

@Repository
public interface RoleRepository  extends JpaRepository<Role, String> {
    Optional<Role> findByRole(RoleType name);
}
