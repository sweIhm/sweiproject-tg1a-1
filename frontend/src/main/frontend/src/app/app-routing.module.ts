import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ActivationComponent } from './activation/activation.component';
import {ViewComponent} from "./view/view.component";
import {DashboardComponent} from "./dashboard/dashboard.component";

const routes: Routes = [
  { path: 'activation/:id/verify', component: ActivationComponent },
  { path: '', component: DashboardComponent },
  { path: 'view/:id', component: ViewComponent}
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
    exports: [ RouterModule ]
})
export class AppRoutingModule { }
