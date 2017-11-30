import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
<<<<<<< HEAD

@NgModule({
  declarations: [
    AppComponent
=======
import { PostComponent } from './post/post.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import {ActivityService} from "./activity.service";
import {HttpClientModule} from "@angular/common/http";
import {NgbModule} from "@ng-bootstrap/ng-bootstrap";
import { AppRoutingModule } from './/app-routing.module';
import { ViewComponent } from './view/view.component';

@NgModule({
  declarations: [
    AppComponent,
    PostComponent,
<<<<<<< HEAD
    DashboardComponent
>>>>>>> 8118831... Post-requests und anzeige der freigeschalteten activities
=======
    DashboardComponent,
    ViewComponent
>>>>>>> ff9e405... -Detailansicht für Activity in Form eines modalen Fensters
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    HttpClientModule,
    NgbModule.forRoot(),
    AppRoutingModule
  ],
  providers: [ActivityService],
  bootstrap: [AppComponent],
  entryComponents: [ViewComponent]
})
export class AppModule { }
