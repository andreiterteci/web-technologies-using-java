package ro.fmi.HeathTracker.model.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.fmi.HeathTracker.domain.enums.RoleType;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RoleModel {
    private RoleType role;
}
