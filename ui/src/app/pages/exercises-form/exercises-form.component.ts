import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {ExerciseModel, ExerciseType, FitnessModel} from "../../shared/models/Fitness";
import {RestResponseModel} from "../../shared/models/RestResponseModel";
import {DialogOptions} from "../../shared/modal-form/dialog-options";
import {MatDialogRef} from "@angular/material/dialog";
import {FitnessService} from "../exercises/fitness.service";
import {MealModel} from "../../shared/models/Meal";
import {ActivatedRoute} from "@angular/router";
import {CustomModalRoutingAndStateService} from "../../shared/custom-modal-routing-and-state-service";

@Component({
  selector: 'app-exercises-form',
  templateUrl: './exercises-form.component.html',
  styleUrls: ['./exercises-form.component.scss']
})
export class ExercisesFormComponent implements OnInit {

  exerciseList: ExerciseModel[] = [];
  @ViewChild('nameInput') nameInput: ElementRef;
  @ViewChild('typeInput') typeInput: ElementRef;
  @ViewChild('durationInput') durationInput: ElementRef;
  dialogOptions = DialogOptions;
  exerciseTypes = ExerciseType;
  selectValues: any[] = [];
  currentRoute: ActivatedRoute;
  currentFitness: FitnessModel = {
    id: null,
    exercises: null,
    duration: null,
    date: null
  };

  constructor(private currentDialogRef: MatDialogRef<ExercisesFormComponent>,
              private fitnessService: FitnessService,
              private customModalRoutingService: CustomModalRoutingAndStateService) {
    this.selectValues = Object.values(this.exerciseTypes);
    this.currentRoute = this.customModalRoutingService.getCurrentRoute();
  }

  ngOnInit(): void {
    this.initializeEdit();
  }

  private initializeEdit() {
    this.currentRoute.params.subscribe(() => {
      const fitnessModel: FitnessModel = this.currentRoute.snapshot.data['exercise']
      if (fitnessModel) {
        this.currentFitness = fitnessModel;
        fitnessModel.exercises.forEach(name => this.exerciseList.push(name));
      }
    })
  }

  addExercise() {
    if (this.nameInput.nativeElement.value != null && this.nameInput.nativeElement.value != "" &&
      this.typeInput.nativeElement.value != null && this.typeInput.nativeElement.value != "" &&
      this.durationInput.nativeElement.value != null && this.durationInput.nativeElement.value != "") {
      let exercise: ExerciseModel = {
        id: null,
        name: this.nameInput.nativeElement.value,
        exerciseType: this.typeInput.nativeElement.value,
        duration: this.durationInput.nativeElement.value
      }
      this.exerciseList.push(exercise);
      this.nameInput.nativeElement.value = null;
      this.typeInput.nativeElement.value = null;
      this.durationInput.nativeElement.value = null;
    }
  }

  removeExercise(exerciseModel: ExerciseModel) {
    const index: number = this.exerciseList.indexOf(exerciseModel);
    if (index !== -1) {
      this.exerciseList.splice(index, 1);
    }
  }

  handleSave() {
    let fitnessModel: FitnessModel = {
      id: this.currentFitness.id,
      exercises: this.exerciseList,
      duration: this.currentFitness.duration,
      date: this.currentFitness.date
    }
    this.save(fitnessModel);
  }

  private save(fitnessModel: FitnessModel) {
    if (fitnessModel.id == null) {
      this.fitnessService
        .create(fitnessModel)
        .subscribe((response: RestResponseModel) => this.handleSaveResponse(response));
    } else {
      this.fitnessService
        .update(fitnessModel)
        .subscribe((response: RestResponseModel) => this.handleSaveResponse(response));
    }
  }

  private handleSaveResponse(response: RestResponseModel) {
    if (response && response.hasOwnProperty('success') && response.hasOwnProperty('errors')) {
      const model = new RestResponseModel(response.success, response.entityId, response.errors);
      if (model.success) {
        this.currentDialogRef.close(DialogOptions.SAVE);
      }
    }
  }

  handleCancel(option: DialogOptions) {
    this.currentDialogRef.close(option);
  }

}
