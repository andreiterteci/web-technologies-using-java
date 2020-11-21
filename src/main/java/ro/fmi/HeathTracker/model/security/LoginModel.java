package ro.fmi.HeathTracker.model.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LoginModel {
    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
