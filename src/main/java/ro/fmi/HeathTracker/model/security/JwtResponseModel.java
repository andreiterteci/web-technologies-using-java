package ro.fmi.HeathTracker.model.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class JwtResponseModel {
    private String id;
    private String token;
    private String type = "Bearer";
    private String email;
    private List<String> roles;
}