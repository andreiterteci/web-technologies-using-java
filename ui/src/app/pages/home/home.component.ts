import {Component, OnInit} from '@angular/core';
import {CustomModalRoutingAndStateService} from "../../shared/custom-modal-routing-and-state-service";
import {MatDialog} from "@angular/material/dialog";
import {HomeService} from "./home.service";
import {HealthModel, Recommendation} from "../../shared/models/Health";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  informationIsLoaded = false;
  health: HealthModel;
  recommenation = Recommendation;

  constructor(private customRoutingService: CustomModalRoutingAndStateService,
              public dialog: MatDialog,
              private homeService: HomeService) {
  }

  ngOnInit(): void {
    this.homeService.getHealthScore().subscribe(score => {
      this.health = score;
      this.informationIsLoaded = true;
    })
  }


  addMeal() {
    this.customRoutingService.navigateToAddMealForm();
  }

  addFitness(){
    this.customRoutingService.navigateToAddExercisesForm();
  }

}
