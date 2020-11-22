import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LoginComponent} from "./auth/login/login.component";
import {LoginPageGuardService} from "./auth/services/login-page-guard.service";
import {RegisterComponent} from "./auth/register/register.component";

const routes: Routes = [
  {
    path: 'login',
    component: LoginComponent,
    canActivate: [LoginPageGuardService]
  },
  {
    path: 'register',
    component: RegisterComponent,
    canActivate: [LoginPageGuardService],
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
