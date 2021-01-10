package ro.fmi.HeathTracker.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.fmi.HeathTracker.model.security.RoleModel;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserModel {
    private String id;

    private String email;

    private String password;

    private String firstName;

    private String lastName;

    private Set<RoleModel> roles;

    private List<DailyDataModel> dailyDataModels;
}
