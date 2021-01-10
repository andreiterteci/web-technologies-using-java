import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {AuthModule} from "./auth/auth.module";
import {AuthService} from "./auth/services/auth.service";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {CustomHttpInterceptorService} from "./auth/services/custom-http-interceptor.service";
import {SidenavComponent} from "./shared/sidenav/sidenav.component";
import {DataTablesModule} from "angular-datatables";
import {ModalFormComponent} from "./shared/modal-form/modal-form.component";
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {CustomModalRoutingAndStateService} from "./shared/custom-modal-routing-and-state-service";
import {CommonResolver} from "./shared/resolvers/common.resolver";
import {MatDialogModule} from "@angular/material/dialog";
import {PagesModule} from "./pages/pages.module";

@NgModule({
  declarations: [
    AppComponent,
    SidenavComponent,
    ModalFormComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    AuthModule,
    HttpClientModule,
    DataTablesModule,
    MatDialogModule,
    BrowserAnimationsModule,
    PagesModule
  ],
  providers: [
    AuthService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: CustomHttpInterceptorService,
      multi: true
    },
    CustomModalRoutingAndStateService,
    CommonResolver
  ]
  ,
  bootstrap: [AppComponent]
})
export class AppModule { }
