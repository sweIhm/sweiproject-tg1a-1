import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { PostactivityComponent } from './components/dashboard/postactivity/postactivity.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { ActivityService} from "./services/activity.service";
import { HttpClientModule} from "@angular/common/http";
import { NgbModule} from "@ng-bootstrap/ng-bootstrap";
import { AppRoutingModule } from './app-routing.module';
import { ViewComponent } from './components/view/view.component';
import { AlertComponent } from './components/alert/alert.component';
import { AlertService } from "./services/alert.service";
import { PostcommentComponent } from './components/postcomment/postcomment.component';
import { CommentService} from "./services/comment.service";
import { FilterComponent } from './components/dashboard/filter/filter.component';
import { FrontpageComponent } from './components/frontpage/frontpage.component';
import { ListComponent } from './components/dashboard/list/list.component';

@NgModule({
  declarations: [
    AppComponent,
    PostactivityComponent,
    DashboardComponent,
    ViewComponent,
    AlertComponent,
    PostcommentComponent,
    FilterComponent,
    FrontpageComponent,
    ListComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    HttpClientModule,
    NgbModule.forRoot(),
    AppRoutingModule
  ],
  providers: [ActivityService, AlertService, CommentService],
  bootstrap: [AppComponent],
  entryComponents: [PostactivityComponent, PostcommentComponent]
})
export class AppModule { }
