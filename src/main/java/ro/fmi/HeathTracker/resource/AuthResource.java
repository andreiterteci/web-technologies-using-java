package ro.fmi.HeathTracker.resource;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ro.fmi.HeathTracker.model.responses.RestResponseModel;
import ro.fmi.HeathTracker.model.security.JwtResponseModel;
import ro.fmi.HeathTracker.model.security.LoginModel;
import ro.fmi.HeathTracker.model.security.SignUpModel;
import ro.fmi.HeathTracker.service.MessageService;
import ro.fmi.HeathTracker.service.UserService;
import ro.fmi.HeathTracker.util.ResponseEntityUtil;

import javax.validation.Valid;

@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthResource {

    private final UserService userService;

    private final MessageService messageService;

    @PostMapping("/signin")
    public ResponseEntity<JwtResponseModel> authenticateUser(@Valid @RequestBody final LoginModel loginModel) {
        return ResponseEntity.ok(userService.authenticateUser(loginModel));
    }

    @PostMapping("/signup")
    public ResponseEntity<RestResponseModel> registerUser(@Valid @RequestBody final SignUpModel signUpRequest,
                                                          final BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntityUtil.getRestResponseModelWithErrors(messageService.getAllErrors(bindingResult));
        }
        return ResponseEntityUtil.getRestResponseModelWithEntityId(userService.registerUser(signUpRequest));
    }
}
