import { Component, OnInit } from '@angular/core';
import {ActivityService} from "../activity.service";
import {Activity} from "../activity";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  activities : Activity[];
  selectedActivity: Activity;
  refreshed: boolean = false;

  constructor(private service:ActivityService) { }

  ngOnInit() {
    this.getActivities();
  }

  onSelect(activity: Activity) {
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
