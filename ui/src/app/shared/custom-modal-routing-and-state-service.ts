import {Injectable} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {Subject} from 'rxjs';
import {DialogOptions} from "./modal-form/dialog-options";

@Injectable()
export class CustomModalRoutingAndStateService {

  returnToParent: Subject<DialogOptions> = new Subject<DialogOptions>();

  constructor(private router: Router) {
  }

  navigateToAddForm() {
    this.router.navigate(['form'], {
      relativeTo: this.getCurrentRoute(),
      queryParams: {formMode: 'add'}
    });
  }

  navigateToAddExercisesForm() {
    this.router.navigate(['exercise-form'], {
      relativeTo: this.getCurrentRoute(),
      queryParams: {formMode: 'add'}
    });
  }

  navigateToAddMealForm() {
    this.router.navigate(['meal-form'], {
      relativeTo: this.getCurrentRoute(),
      queryParams: {formMode: 'add'}
    });
  }

  navigateToEditForm(id: string) {
    this.router.navigate(['form'], {
      relativeTo: this.getCurrentRoute(),
      queryParams: {id: id, formMode: 'edit'}
    });
  }

  getCurrentRoute(): ActivatedRoute {
    let currentRoute = this.router.routerState.root;
    while (currentRoute.firstChild) {
      currentRoute = currentRoute.firstChild;
    }
    return currentRoute;
  }

  emitReturnToParent(option: DialogOptions) {
    this.returnToParent.next(option);
  }
}
