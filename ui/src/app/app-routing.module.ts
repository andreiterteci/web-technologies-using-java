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
import {ModalFormComponent} from "./shared/modal-form/modal-form.component";
import {environment} from "../environments/environment";
import {CommonResolver} from "./shared/resolvers/common.resolver";
import {MealFormComponent} from "./pages/meal-form/meal-form.component";
import {ExercisesFormComponent} from "./pages/exercises-form/exercises-form.component";
import {AccountFormComponent} from "./pages/account-form/account-form.component";

const url: string = environment.url;
const routes: Routes = [
  {
    path: '', canActivate: [AuthGuardService] , component: SidenavComponent, children: [
      {
        path: 'home',
        component: HomeComponent,
        canActivate: [AuthGuardService],
        children: [
          {
            path: 'meal-form',
            component: ModalFormComponent,
            data: {
              dialogContent: MealFormComponent,
              dialogConfig: {
                height: '50vh',
                width: '30vw'
              }
            },
            resolve:{
              meal: CommonResolver
            }
          },
          {
            path: 'exercise-form',
            component: ModalFormComponent,
            data: {
              dialogContent: ExercisesFormComponent,
              dialogConfig: {
                height: '50vh',
                width: '30vw'
              }
            },
            resolve:{
              exercise: CommonResolver
            }
          }
        ],
        runGuardsAndResolvers: 'always'
      },
      {
        path: 'exercises',
        component: ExercisesComponent,
        canActivate: [AuthGuardService],
        children: [
          {
            path: 'form',
            component: ModalFormComponent,
            data: {
              dialogContent: ExercisesFormComponent,
              dialogConfig: {
                height: '50vh',
                width: '30vw'
              },
              endpoint: url + 'api/fitness/'
            },
            resolve:{
              exercise: CommonResolver
            }
          }
        ]
      },
      {
        path: 'meals',
        component: MealsComponent,
        canActivate: [AuthGuardService],
        children: [
          {
            path: 'form',
            component: ModalFormComponent,
            data: {
              dialogContent: MealFormComponent,
              dialogConfig: {
                height: '50vh',
                width: '30vw'
              },
              endpoint: url + 'api/meal/'
            },
            resolve:{
              meal: CommonResolver
            }
          }
        ]
      },
      {
        path: 'account',
        component: AccountComponent,
        canActivate: [AuthGuardService],
        children: [
          {
            path: 'form',
            component: ModalFormComponent,
            data: {
              dialogContent: AccountFormComponent,
              dialogConfig: {
                height: '70vh',
                width: '50vw'
              },
              endpoint: url + 'api/account/'
            },
            resolve:{
              account: CommonResolver
            }
          }
        ]
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
