import {Component, OnInit} from '@angular/core';
import {AccountModel} from "../../shared/models/User";
import {AccountService} from "./account.service";
import {CustomModalRoutingAndStateService} from "../../shared/custom-modal-routing-and-state-service";

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.scss']
})
export class AccountComponent implements OnInit {

  currentAccount: AccountModel;
  informationIsLoaded: boolean = false;

  constructor(private accountService: AccountService,
              private customRoutingService: CustomModalRoutingAndStateService) {
  }

  ngOnInit(): void {
    this.initAccount();
  }

  private initAccount() {
    this.accountService.getCurrent().subscribe(account => {
      this.currentAccount = account;
      this.informationIsLoaded = true;
    })
  }

  updateAccount() {
    this.customRoutingService.navigateToEditForm(this.currentAccount.id);
  }

}
