import { Component, OnInit } from '@angular/core';
import {ActivityService} from "../../services/activity.service";
import {Activity} from "../../model/activity";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {AlertService} from "../../services/alert.service";
import {ActivatedRoute} from "@angular/router";
import {PostactivityComponent} from "./postactivity/postactivity.component";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  activities : Activity[] = [];

  constructor(private service: ActivityService,
              private modal: NgbModal,
              private alertService: AlertService,
              private route: ActivatedRoute) { }

  ngOnInit() {
    let alert = this.route.snapshot.paramMap.get('alert');
    this.addAlert(alert);
    let query = this.route.snapshot.paramMap.get('filter');
    this.getActivities();
  }

  getActivities() {
    this.service.getActivities().subscribe(activities => this.activities = activities);
  }

  refresh() {
    this.getActivities();
    this.alertService.addAlert('Data refreshed!', 'info')
  }

  openPostModal() {
    this.modal.open(PostactivityComponent);
  }

  addAlert(alert: string) {
    if (alert == 'activityactivationfailed') {
      this.alertService.addAlert('Activation failed! Try submitting your activity again.', 'danger');
    }
    if (alert == 'commentactivationfailed') {
      this.alertService.addAlert('Activation failed! Try submitting your comment again.', 'danger');
    }
    // TODO Dieser alert gehört eigentlich zu view
    if (alert == 'commentactivationsucceeded') {
      this.alertService.addAlert('Comment successfully published. Thank you for your submission!', 'success');
    }
  }

}
