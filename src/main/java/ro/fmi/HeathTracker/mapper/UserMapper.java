package ro.fmi.HeathTracker.mapper;

import ro.fmi.HeathTracker.domain.Role;
import ro.fmi.HeathTracker.domain.User;
import ro.fmi.HeathTracker.model.security.SignUpModel;

import java.util.Set;

public final class UserMapper {

    private UserMapper(){}

    public static User toRegister(final SignUpModel signUpModel, final String encodedPassword, final Set<Role> roles) {
        return User.builder()
                .firstName(signUpModel.getFirstName())
                .lastName(signUpModel.getLastName())
                .password(encodedPassword)
                .roles(roles)
                .email(signUpModel.getEmail())
                .build();
    }
}
