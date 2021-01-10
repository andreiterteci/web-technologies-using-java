import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {HomeComponent} from './home/home.component';
import {ExercisesComponent} from './exercises/exercises.component';
import {MealsComponent} from './meals/meals.component';
import {AccountComponent} from './account/account.component';
import {MealService} from "./meals/meal.service";
import {MealFormComponent} from './meal-form/meal-form.component';
import {AppRoutingModule} from "../app-routing.module";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {RouterModule} from "@angular/router";
import { ExercisesFormComponent } from './exercises-form/exercises-form.component';
import {MatSelectModule} from "@angular/material/select";
import {FitnessService} from "./exercises/fitness.service";
import { AccountFormComponent } from './account-form/account-form.component';


@NgModule({
  declarations: [
    HomeComponent,
    ExercisesComponent,
    MealsComponent,
    AccountComponent,
    MealFormComponent,
    ExercisesFormComponent,
    AccountFormComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule,
    AppRoutingModule,
  ],
  exports: [
    HomeComponent,
    ExercisesComponent,
    MealsComponent,
    AccountComponent,
    MealFormComponent
  ],
  providers: [MealService, FitnessService]
})
export class PagesModule {
}
