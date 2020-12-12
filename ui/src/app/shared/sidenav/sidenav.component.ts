import { Component, OnInit } from '@angular/core';
import {TokenStorageService} from "../../auth/services/token-storage.service";

@Component({
  selector: 'app-sidenav',
  templateUrl: './sidenav.component.html',
  styleUrls: ['./sidenav.component.scss']
})
export class SidenavComponent implements OnInit {

  constructor(
    public tokenStorageService: TokenStorageService,
  ) {
  }

  ngOnInit(): void {
  }

}
