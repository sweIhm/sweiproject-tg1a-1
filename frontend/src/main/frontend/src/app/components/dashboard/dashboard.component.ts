import { Component, OnInit } from '@angular/core';
import {ActivityService} from "../../services/activity.service";
import {Activity} from "../../model/activity";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {PostComponent} from "../post/post.component";
import {AlertService} from "../../services/alert.service";
import {ActivatedRoute} from "@angular/router";

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
    const param = this.route.snapshot.paramMap.get('alert');
    if (param == 'activationfailed') {
      this.alertService.addAlert('Activation failed! Try submitting your activity again.', 'danger');
    }
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
    this.modal.open(PostComponent);
  }

  activitiesIsEmpty(): boolean {
    if (this.activities)
      return this.activities.length == 0;
    return false;
  }
}
