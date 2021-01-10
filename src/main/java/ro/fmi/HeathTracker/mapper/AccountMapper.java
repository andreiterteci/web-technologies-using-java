package ro.fmi.HeathTracker.mapper;

import ro.fmi.HeathTracker.domain.User;
import ro.fmi.HeathTracker.model.dto.AccountModel;

public class AccountMapper {

    private AccountMapper(){}

    public static AccountModel toModel(final User user) {
        return AccountModel.builder()
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .fitnessPlan(user.getFitnessPlan())
                .gender(user.getGender())
                .height(user.getHeight())
                .weight(user.getWeight())
                .weightGoal(user.getWeightGoal())
                .id(user.getId())
                .build();
    }
}
