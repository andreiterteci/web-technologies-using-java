import {Component, OnInit} from '@angular/core';
import {MealModel} from "../../shared/models/Meal";
import {ExerciseModel, FitnessModel} from "../../shared/models/Fitness";
import {FitnessService} from "./fitness.service";
import {CustomModalRoutingAndStateService} from "../../shared/custom-modal-routing-and-state-service";
import {MatDialogRef} from "@angular/material/dialog";
import {ExercisesFormComponent} from "../exercises-form/exercises-form.component";
import {RestResponseModel} from "../../shared/models/RestResponseModel";

@Component({
  selector: 'app-exercises',
  templateUrl: './exercises.component.html',
  styleUrls: ['./exercises.component.scss']
})
export class ExercisesComponent implements OnInit {

  fitness: FitnessModel[] = [];
  informationIsLoaded = false;
  dtOptions: DataTables.Settings = {};

  constructor(private fitnessService: FitnessService,
              private customModalRoutingService: CustomModalRoutingAndStateService) {
  }

  ngOnInit(): void {
    this.dtOptions = {
      pagingType: 'full_numbers',
      serverSide: true,
      processing: true
    };
    this.fitnessService.getAll().subscribe(fitness => {
      this.fitness = fitness;
      this.informationIsLoaded = true;
    });
    setTimeout(function () {
      $(function () {
        $('#fitnessTable').DataTable();
      });
    }, 2000);
  }

  getFormattedExercises(exercises: ExerciseModel[]) {
    return  exercises.length > 0 ? exercises.map(exercise => exercise.name).reduce((x, y) => x + ", " + y) : "";
  }

  delete(fitness: FitnessModel) {
    this.fitnessService.delete(fitness.id)
      .subscribe((response: RestResponseModel) => this.handleResponse(response, fitness));
  }

  update(id: string) {
    this.customModalRoutingService.navigateToEditForm(id);
  }

  private handleResponse(response: RestResponseModel, fitness: FitnessModel) {
    if (response && response.hasOwnProperty('success') && response.hasOwnProperty('errors')) {
      const model = new RestResponseModel(response.success, response.entityId, response.errors);
      if (model.success) {
        const index: number = this.fitness.indexOf(fitness);
        if (index !== -1) {
          this.fitness.splice(index, 1);
        }
      }
    }
  }
}
