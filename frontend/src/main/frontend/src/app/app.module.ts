import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { RouterModule, Routes } from '@angular/router';

import { AppComponent } from './app.component';
import { PostComponent } from './components/post/post.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import {ActivityService} from "./services/activity.service";
import {HttpClientModule} from "@angular/common/http";
import {NgbModule} from "@ng-bootstrap/ng-bootstrap";
import { AppRoutingModule } from './app-routing.module';
import { ViewComponent } from './components/view/view.component';
import { AlertComponent } from './components/alert/alert.component';
import {AlertService} from "./services/alert.service";

@NgModule({
  declarations: [
    AppComponent,
    PostComponent,
    DashboardComponent,
    ViewComponent,
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
export class AppModule {
  const appRoutes: Routes = [
  { path: 'show/post/:id', ViewComponent },

}
