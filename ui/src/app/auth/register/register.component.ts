import { Component, OnInit } from '@angular/core';
import {User, UserLoginData} from "../../shared/models/User";
import {FormControl, FormGroup, ValidatorFn, Validators} from "@angular/forms";
import {AuthService} from "../services/auth.service";
import {Router} from "@angular/router";
import {TokenStorageService} from "../services/token-storage.service";
import {RestResponseModel} from "../../shared/models/RestResponseModel";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  registerForm: FormGroup;
  roles: string[] = [];
  errorMessage: string = '';

  constructor(private authService: AuthService,
              private router: Router,
              private tokenStorage: TokenStorageService) {
    this.registerForm = new FormGroup({
      firstName: new FormControl(null, [Validators.required]),
      lastName: new FormControl(null, [Validators.required]),
      email: new FormControl(null, [Validators.required, Validators.email]),
      password: new FormControl(null, [Validators.required,
        Validators.pattern('.*[0-9].*'), Validators.minLength(6)]),
      passwordMatcher: new FormControl(null, Validators.required)
    }
      // { validators: [this.validatePasswordsMatch.bind]}
      );
  }

  ngOnInit(): void {
  }

  validatePasswordsMatch(formGroup: FormGroup) {
    const password = formGroup.get('password')!.value;
    const passwordMatcher = formGroup.get('passwordMatcher')!.value;

    if (formGroup.get('passwordMatcher')!.touched) {
      if (password != passwordMatcher) {
        return {'match': true};
      }
    }
    return null;
  };

  handleRegister() {
    let userModel: User;

    if (this.registerForm.valid) {
      userModel = {
        ...this.registerForm.getRawValue()
      };
      console.log(userModel);
      this.save(userModel);
    }
  }

  private save(user: User) {
    this.authService
      .register(user)
      .subscribe((response: RestResponseModel) => this.handleSaveResponse(response, user),
          (error: { error: { message: string; }; }) => this.errorMessage = error.error.message);
  }


  private handleSaveResponse(response: RestResponseModel, user: User) {
    if (response && response.hasOwnProperty('success') && response.hasOwnProperty('errors')) {
      const model = new RestResponseModel(response.success, response.entityId, response.errors);
      if (model.success) {
        this.authService.login(user).subscribe(
          (data: UserLoginData) => {
            this.tokenStorage.saveToken(data.token);
            this.tokenStorage.saveUser(data);
            this.roles = this.tokenStorage.getUser().roles;
            this.navigateToHome();
          });
      }
    }
  }

  public navigateToLogin() {
    this.router.navigate(['login']);
  }

  public navigateToHome() {
    this.router.navigate(['home']);
  }

}
