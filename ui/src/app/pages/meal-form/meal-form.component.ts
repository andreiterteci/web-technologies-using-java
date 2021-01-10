import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {MealModel} from "../../shared/models/Meal";
import {FormControl, FormGroup} from "@angular/forms";
import {MealService} from "../meals/meal.service";
import {RestResponseModel} from "../../shared/models/RestResponseModel";
import {MatDialogRef} from "@angular/material/dialog";
import {DialogOptions} from "../../shared/modal-form/dialog-options";
import {ActivatedRoute} from "@angular/router";
import {CustomModalRoutingAndStateService} from "../../shared/custom-modal-routing-and-state-service";

@Component({
  selector: 'app-meal-form',
  templateUrl: './meal-form.component.html',
  styleUrls: ['./meal-form.component.scss']
})
export class MealFormComponent implements OnInit {

  mealNames: string[] = [];
  @ViewChild('nameInput') nameInput: ElementRef;
  mealForm: FormGroup;
  dialogOptions = DialogOptions;
  currentRoute: ActivatedRoute;

  constructor(private currentDialogRef: MatDialogRef<MealFormComponent>,
              private mealService: MealService,
              private customModalRoutingService: CustomModalRoutingAndStateService) {
    this.currentRoute = this.customModalRoutingService.getCurrentRoute();
  }

  ngOnInit(): void {
    this.initializeForm();
    this.initializeEdit();
  }

  private initializeForm() {
    this.mealForm = new FormGroup({
      id: new FormControl(null),
      names: new FormControl(null),
      calories: new FormControl(null),
    })
  }

  private initializeEdit() {
    this.currentRoute.params.subscribe(() => {
      const meal: MealModel = this.currentRoute.snapshot.data['meal']
      if (meal) {
        this.mealForm.patchValue({...meal});
        meal.names.forEach(name => this.mealNames.push(name));
      }
    })
  }

  addMeal() {
    if (this.nameInput.nativeElement.value != null && this.nameInput.nativeElement.value != "") {
      this.mealNames.push(this.nameInput.nativeElement.value);
      this.nameInput.nativeElement.value = null;
    }
  }

  removeMeal(value: string) {
    const index: number = this.mealNames.indexOf(value);
    if (index !== -1) {
      this.mealNames.splice(index, 1);
    }
  }

  handleSave() {
    let mealModel: MealModel;

    if (this.mealForm.valid) {
      mealModel = {
        ...this.mealForm.getRawValue(),
      };
      mealModel.names = this.mealNames;
      this.save(mealModel);
    }
  }

  private save(mealModel: MealModel) {
    if (mealModel.id == null){
      this.mealService
        .create(mealModel)
        .subscribe((response: RestResponseModel) => this.handleSaveResponse(response));
    } else {
      this.mealService
        .update(mealModel)
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
