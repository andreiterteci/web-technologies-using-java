import {Component, OnInit} from '@angular/core';
import {MatDialogRef} from "@angular/material/dialog";
import {CustomModalRoutingAndStateService} from "../../shared/custom-modal-routing-and-state-service";
import {AccountService} from "../account/account.service";
import {DialogOptions} from "../../shared/modal-form/dialog-options";
import {ActivatedRoute} from "@angular/router";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {AccountModel, FitnessPlan, Gender} from "../../shared/models/User";
import {MealModel} from "../../shared/models/Meal";
import {RestResponseModel} from "../../shared/models/RestResponseModel";

@Component({
  selector: 'app-account-form',
  templateUrl: './account-form.component.html',
  styleUrls: ['./account-form.component.scss']
})
export class AccountFormComponent implements OnInit {

  dialogOptions = DialogOptions;
  currentRoute: ActivatedRoute;
  accountForm: FormGroup;
  gender = Gender;
  genderValues: any[] = [];
  fitnessPlans = FitnessPlan;
  planValues: any[] = [];

  constructor(private currentDialogRef: MatDialogRef<AccountFormComponent>,
              private accountService: AccountService,
              private customModalRoutingService: CustomModalRoutingAndStateService) {
    this.currentRoute = this.customModalRoutingService.getCurrentRoute();
    this.genderValues = Object.values(this.gender);
    this.planValues = Object.values(this.fitnessPlans);
  }

  ngOnInit(): void {
    this.initForm();
    this.initEdit();
  }

  private initForm() {
    this.accountForm = new FormGroup({
      firstName: new FormControl(null, Validators.required),
      lastName: new FormControl(null, Validators.required),
      email: new FormControl(null, Validators.required),
      gender: new FormControl(null),
      weight: new FormControl(null),
      height: new FormControl(null),
      fitnessPlan: new FormControl(null),
      weightGoal: new FormControl(null)
    });
  }

  private initEdit() {
    this.currentRoute.params.subscribe(() => {
      const account: AccountModel = this.currentRoute.snapshot.data['account'];
      if (account) {
        this.accountForm.patchValue({...account});
      }
    })
  }

  handleSave() {
    let accountModel: AccountModel;

    if (this.accountForm.valid) {
      accountModel = {
        ...this.accountForm.getRawValue(),
      };
      if (accountModel.fitnessPlan == this.fitnessPlans.MAINTAIN_WEIGHT) {
        accountModel.weightGoal = null;
      }
      this.save(accountModel);
    }
  }

  private save(accountModel: AccountModel) {
    this.accountService
      .update(accountModel)
      .subscribe((response: RestResponseModel) => this.handleSaveResponse(response));
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
