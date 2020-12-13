import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {HomeComponent} from './home/home.component';
import {ExercisesComponent} from './exercises/exercises.component';
import {MealsComponent} from './meals/meals.component';
import {AccountComponent} from './account/account.component';
import {MealService} from "./meals/meal.service";


@NgModule({
  declarations: [HomeComponent, ExercisesComponent, MealsComponent, AccountComponent],
  imports: [
    CommonModule
  ],
  exports: [HomeComponent, ExercisesComponent, MealsComponent, AccountComponent],
  providers: [MealService]
})
export class PagesModule {
}
