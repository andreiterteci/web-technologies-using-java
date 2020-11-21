package ro.fmi.HeathTracker.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ro.fmi.HeathTracker.domain.Role;
import ro.fmi.HeathTracker.domain.User;
import ro.fmi.HeathTracker.domain.enums.RoleType;
import ro.fmi.HeathTracker.exception.RoleNotFoundException;
import ro.fmi.HeathTracker.exception.UserAlreadyExistsException;
import ro.fmi.HeathTracker.mapper.UserMapper;
import ro.fmi.HeathTracker.model.security.JwtResponseModel;
import ro.fmi.HeathTracker.model.security.LoginModel;
import ro.fmi.HeathTracker.model.security.SignUpModel;
import ro.fmi.HeathTracker.repository.RoleRepository;
import ro.fmi.HeathTracker.repository.UserRepository;
import ro.fmi.HeathTracker.security.UserDetailsImpl;
import ro.fmi.HeathTracker.util.JwtUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder encoder;

    private final JwtUtils jwtUtils;

    public JwtResponseModel authenticateUser(final LoginModel loginModel) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginModel.getEmail(), loginModel.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return JwtResponseModel.builder()
                .id(userDetails.getId())
                .type("Bearer")
                .token(jwt)
                .email(userDetails.getEmail())
                .roles(roles)
                .build();
    }

    public String registerUser(final SignUpModel signUpModel) {
        if (userRepository.existsByEmail(signUpModel.getEmail()) ||
                userRepository.existsByEmail(signUpModel.getEmail())) {
            throw new UserAlreadyExistsException();
        }

        Set<String> strRoles = signUpModel.getRoles();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByRole(RoleType.ROLE_USER)
                    .orElseThrow(RoleNotFoundException::new);
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                if (role.equals("admin")) {
                    Role adminRole = roleRepository.findByRole(RoleType.ROLE_ADMIN)
                            .orElseThrow(RoleNotFoundException::new);
                    roles.add(adminRole);
                } else if (role.equals("user")) {
                    Role userRole = roleRepository.findByRole(RoleType.ROLE_USER)
                            .orElseThrow(RoleNotFoundException::new);
                    roles.add(userRole);
                }
            });
        }
        User user = UserMapper.toRegister(signUpModel, encoder.encode(signUpModel.getPassword()), roles);
        userRepository.save(user);

        return user.getId();
    }
}
