import { Component, OnInit } from '@angular/core';
import {ActivityService} from "../../services/activity.service";
import {Activity} from "../../model/activity";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {PostComponent} from "../post/post.component";
import {AlertService} from "../../services/alert.service";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  activities : Activity[];

  constructor(private service: ActivityService, private modal: NgbModal, private alertService: AlertService) { }

  ngOnInit() {
    this.getActivities();
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
}
