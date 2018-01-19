import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DetailsComponent } from "./components/details/details.component";
import { DashboardComponent } from "./components/dashboard/dashboard.component";
import { FrontpageComponent } from './components/frontpage/frontpage.component';

const routes: Routes = [
  { path: '', component: FrontpageComponent, pathMatch: 'full' },
  { path: 'dashboard', component: DashboardComponent },
  { path: 'view/:id', component: DetailsComponent },
  { path: 'details/:id', component: DetailsComponent }
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
    exports: [ RouterModule ]
})
export class AppRoutingModule { }
