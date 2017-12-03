import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FrontpageComponent } from './frontpage/frontpage.component';
import { ActivationComponent } from './activation/activation.component';
import {ViewComponent} from "./view/view.component";

const routes: Routes = [
  { path: '', component: FrontpageComponent },
  { path: 'activation/:id/verify', component: ActivationComponent },
  { path: 'view/:id', component: ViewComponent}
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
    exports: [ RouterModule ]
})
export class AppRoutingModule { }
