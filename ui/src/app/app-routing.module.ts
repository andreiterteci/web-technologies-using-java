import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LoginComponent} from "./auth/login/login.component";
import {LoginPageGuardService} from "./auth/services/login-page-guard.service";
import {RegisterComponent} from "./auth/register/register.component";
import {SidenavComponent} from "./shared/sidenav/sidenav.component";
import {ExercisesComponent} from "./pages/exercises/exercises.component";
import {AuthGuardService} from "./auth/services/auth-guard.service";
import {MealsComponent} from "./pages/meals/meals.component";
import {HomeComponent} from "./pages/home/home.component";
import {AccountComponent} from "./pages/account/account.component";

const routes: Routes = [
  {
    path: '', canActivate: [AuthGuardService] , component: SidenavComponent, children: [
      {
        path: 'home',
        component: HomeComponent,
        canActivate: [AuthGuardService],
      },
      {
        path: 'exercises',
        component: ExercisesComponent,
        canActivate: [AuthGuardService],
      },
      {
        path: 'meals',
        component: MealsComponent,
        canActivate: [AuthGuardService],
      },
      {
        path: 'account',
        component: AccountComponent,
        canActivate: [AuthGuardService],
      }
    ]
  },
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
