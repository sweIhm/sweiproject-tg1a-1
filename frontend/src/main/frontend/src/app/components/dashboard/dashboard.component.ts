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

  activities : Activity[];

  constructor(private service: ActivityService,
              private modal: NgbModal,
              private alertService: AlertService,
              private route: ActivatedRoute) { }

  ngOnInit() {
    this.getActivities();
    var param = this.route.snapshot.paramMap.get('alert');
    this.addAlert(param);
    // TODO alerts für comments handeln
  }

  getActivities() {
    this.service.getActivities().subscribe(activities => this.activities = activities);
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
}
