package ro.fmi.HeathTracker.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.fmi.HeathTracker.domain.enums.FitnessPlan;
import ro.fmi.HeathTracker.domain.enums.Gender;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AccountModel {

    private String id;

    private String email;

    private String firstName;

    private String lastName;

    private Gender gender;

    private Double weight;

    private Double height;

    private FitnessPlan fitnessPlan;

    private Double weightGoal;
}
