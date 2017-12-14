import { Component, OnInit } from '@angular/core';
import {ActivityService} from "../../services/activity.service";
import {Activity} from "../../model/activity";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {AlertService} from "../../services/alert.service";
import {ActivatedRoute} from "@angular/router";
import {PostactivityComponent} from "../postactivity/postactivity.component";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  activities : Activity[] = [];
  filtered : Activity[] = [];
  filterEnabled: boolean = false;
  query: string;

  constructor(private service: ActivityService,
              private modal: NgbModal,
              private alertService: AlertService,
              private route: ActivatedRoute) { }

  ngOnInit() {
    let alert = this.route.snapshot.paramMap.get('alert');
    this.addAlert(alert);
    let query = this.route.snapshot.paramMap.get('filter');
    if (query) {
      this.query = query;
      this.filterEnabled = true;
    }
    this.getActivities();
  }

  getActivities() {
    this.service.getActivities().subscribe(activities => this.initActivities(activities));
  }

  initActivities(activities: Activity[]) {
    this.activities = activities;
    if (this.filterEnabled) {
      this.filter();
    }
  }

  refresh() {
    this.getActivities();
    this.alertService.addAlert('Data refreshed!', 'success')
  }

  openPostModal() {
    this.modal.open(PostactivityComponent);
  }

  hasActivities(): boolean {
    if (this.activities)
      return this.activities.length > 0;
    return false;
  }

  addAlert(alert: string) {
    if (alert == 'activityactivationfailed') {
      this.alertService.addAlert('Activation failed! Try submitting your activity again.', 'danger');
    }
    if (alert == 'commentactivationfailed') {
      this.alertService.addAlert('Activation failed! Try submitting your comment again.', 'danger');
    }
    if (alert == 'commentactivationsucceeded') {
      this.alertService.addAlert('Comment successfully published. Thank you for your submission!', 'success');
    }
  }

  filter() {
    this.filterEnabled = true;
    this.filtered = [];
    for (let activity of this.activities) {
      if (activity.title == this.query) {
        this.filtered.push(activity);
      }
      else if (activity.author == this.query) {
        this.filtered.push(activity);
      }
      else {
        for (let keyword of activity.keywords) {
          if (keyword.content == this.query) {
            this.filtered.push(activity);
            break;
          }
        }
      }
    }
  }

  removeFilter() {
    this.query = "";
    this.filterEnabled = false;
  }

}
