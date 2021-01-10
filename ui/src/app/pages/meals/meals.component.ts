import {Component, OnInit} from '@angular/core';
import {MealService} from "./meal.service";
import {MealModel} from "../../shared/models/Meal";
import {CustomModalRoutingAndStateService} from "../../shared/custom-modal-routing-and-state-service";
import {RestResponseModel} from "../../shared/models/RestResponseModel";

@Component({
  selector: 'app-meals',
  templateUrl: './meals.component.html',
  styleUrls: ['./meals.component.scss']
})
export class MealsComponent implements OnInit {

  meals: MealModel[] = [];
  informationIsLoaded = false;
  dtOptions: DataTables.Settings = {};

  constructor(private mealService: MealService,
              private customRoutingService: CustomModalRoutingAndStateService) {
    setTimeout(function () {
      $(function () {
        $('#mealTable').DataTable();
      });
    }, 2000);
  }

  ngOnInit(): void {
    this.dtOptions = {
      pagingType: 'full_numbers',
      serverSide: true,
      processing: true
    };
    this.mealService.getAll().subscribe(meals => {
      this.meals = meals;
      this.informationIsLoaded = true;
    });
    setTimeout(function () {
      $(function () {
        $('#mealTable').DataTable();
      });
    }, 2000);
  }

  getFormattedMeals(meals: string[]) {
    return meals.length > 0 ? meals.reduce((x, y) => x + ", " + y) : "";
  }

  update(id: string) {
    this.customRoutingService.navigateToEditForm(id);
  }

  delete(meal: MealModel) {
    this.mealService.delete(meal.id)
      .subscribe((response: RestResponseModel) => this.handleResponse(response, meal));
  }

  private handleResponse(response: RestResponseModel, meal: MealModel) {
    if (response && response.hasOwnProperty('success') && response.hasOwnProperty('errors')) {
      const model = new RestResponseModel(response.success, response.entityId, response.errors);
      if (model.success) {
        const index: number = this.meals.indexOf(meal);
        if (index !== -1) {
          this.meals.splice(index, 1);
        }
      }
    }
  }

}
