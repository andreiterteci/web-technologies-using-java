import {Component} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {CustomModalRoutingAndStateService} from "../custom-modal-routing-and-state-service";
import {DialogOptions} from "./dialog-options";
import {MatDialog} from "@angular/material/dialog";

@Component({
  template: '',
})
export class ModalFormComponent {
  constructor(public dialog: MatDialog,
              private router: Router,
              private route: ActivatedRoute,
              private customModalRoutingAndStateService: CustomModalRoutingAndStateService) {
    this.openDialog();
  }

  openDialog(): void {
    const dialogContentComponent = this.route.snapshot.data['dialogContent'];
    const dialogConfig = this.route.snapshot.data['dialogConfig'];
    const dialogRef = this.dialog.open(dialogContentComponent,  {
      panelClass: 'modal-wrapper',
      disableClose: true,
      height: 'calc(100vh - 180px)',
      width: '100vw',
      autoFocus: true,
      ...dialogConfig,
    });

    dialogRef.afterClosed().subscribe((result: DialogOptions) => {
      this.router.navigate(['../'], {relativeTo: this.route});
      this.customModalRoutingAndStateService.emitReturnToParent(result);
    });

    dialogRef.keydownEvents().subscribe((keyboard: KeyboardEvent) => {
      if (keyboard.key && keyboard.key.toLowerCase() === 'escape') {
        dialogRef.close();
      }
    });
  }
}
