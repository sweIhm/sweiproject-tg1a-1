import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { PostactivityComponent } from './components/dashboard/createactivity/postactivity.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { ActivityService} from "./services/activity.service";
import { HttpClientModule} from "@angular/common/http";
import { NgbModule} from "@ng-bootstrap/ng-bootstrap";
import { AppRoutingModule } from './app-routing.module';
import { AlertComponent } from './components/alert/alert.component';
import { AlertService } from "./services/alert.service";
import { PostcommentComponent } from './components/details/postcomment/postcomment.component';
import { CommentService} from "./services/comment.service";
import { FilterComponent } from './components/dashboard/filter/filter.component';
import { FrontpageComponent } from './components/frontpage/frontpage.component';
import { ListComponent } from './components/dashboard/list/list.component';
import { DetailsComponent } from './components/details/details.component';
import { ActivityComponent } from './components/details/activity/activity.component';
import { CommentComponent } from './components/details/comment/comment.component';
import { SearchactivityComponent } from './components/dashboard/searchactivity/searchactivity.component';

@NgModule({
  declarations: [
    AppComponent,
    PostactivityComponent,
    DashboardComponent,
    AlertComponent,
    PostcommentComponent,
    FilterComponent,
    FrontpageComponent,
    ListComponent,
    DetailsComponent,
    ActivityComponent,
    CommentComponent,
    SearchactivityComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    NgbModule.forRoot(),
    AppRoutingModule
  ],
  providers: [ActivityService, AlertService, CommentService],
  bootstrap: [AppComponent],
  entryComponents: [PostactivityComponent, PostcommentComponent, SearchactivityComponent]
})
export class AppModule { }
