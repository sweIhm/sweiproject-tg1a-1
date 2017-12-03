import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { PostComponent } from './post/post.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import {ActivityService} from "./activity.service";
import {HttpClientModule} from "@angular/common/http";
import {NgbModule} from "@ng-bootstrap/ng-bootstrap";
import { AppRoutingModule } from './app-routing.module';
import { ViewComponent } from './view/view.component';
import { ActivationComponent } from './activation/activation.component';
import { AlertComponent } from './alert/alert.component';
import {AlertService} from "./alert.service";

@NgModule({
  declarations: [
    AppComponent,
    PostComponent,
    DashboardComponent,
    ViewComponent,
    ActivationComponent,
    AlertComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    HttpClientModule,
    NgbModule.forRoot(),
    AppRoutingModule
  ],
  providers: [ActivityService, AlertService],
  bootstrap: [AppComponent],
  entryComponents: [PostComponent]
})
export class AppModule { }
