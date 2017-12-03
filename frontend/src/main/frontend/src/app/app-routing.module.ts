import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { FrontpageComponent } from './frontpage/frontpage.component';
import { ActivationComponent } from './activation/activation.component';

const routes: Routes = [
  { path: '', component: FrontpageComponent },
  { path: 'activation/:id', component: ActivationComponent }
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
    exports: [ RouterModule ]
})
export class AppRoutingModule { }
