import { Component, OnInit } from '@angular/core';
import {ActivityService} from "../activity.service";
import {Activity} from "../activity";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {ViewComponent} from "../view/view.component";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  activities : Activity[];
  refreshed: boolean = false;
  selectedActivity: Activity;

  constructor(private service:ActivityService, private modal:NgbModal) { }

  ngOnInit() {
    this.getActivities();
  }

  selectActivity(activity: Activity) {
    this.selectedActivity = activity;
  }

  getActivities() {
    this.service.getActivities().subscribe(activities => this.activities = activities);
  }

  refresh() {
    this.getActivities();
    this.refreshed = true;
  }
}
