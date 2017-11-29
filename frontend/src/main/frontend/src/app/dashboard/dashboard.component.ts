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

  constructor(private service:ActivityService) { }

  ngOnInit() {
    this.service.getActivities().subscribe(activities => this.activities = activities);
  }

  onSelect(activity: Activity) {
    this.selectedActivity = activity;
  }
}
