import {Component, OnInit} from '@angular/core';
import {MealService} from "./meal.service";
import {MealModel} from "../../shared/models/Meal";

@Component({
  selector: 'app-meals',
  templateUrl: './meals.component.html',
  styleUrls: ['./meals.component.scss']
})
export class MealsComponent implements OnInit {

  meals: MealModel[] = [];
  informationIsLoaded = false;
  dtOptions: DataTables.Settings = {};

  constructor(private mealService: MealService) {
    setTimeout(function(){
      $(function(){
        $('#mealTable').DataTable();
      });
    },2000);
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
    setTimeout(function(){
      $(function(){
        $('#mealTable').DataTable();
      });
    },2000);
  }

  update(id: string){

  }

  delete(id: string){

  }

}
