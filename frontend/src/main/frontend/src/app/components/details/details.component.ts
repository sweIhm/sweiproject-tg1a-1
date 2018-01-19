import {Component, OnInit} from '@angular/core';
import {Activity} from "../../model/activity";
import {ActivatedRoute} from "@angular/router";
import {ActivityService} from "../../services/activity.service";
import {AlertService} from "../../services/alert.service";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {PostcommentComponent} from "./createcomment/postcomment.component";
import {CommentService} from "../../services/comment.service";

@Component({
  selector: 'app-details',
  templateUrl: './details.component.html',
  styleUrls: ['./details.component.css']
})
export class DetailsComponent implements OnInit {

  activity: Activity;
  comments: Comment[] = [];

  constructor (
    private route: ActivatedRoute,
    private activityService: ActivityService,
    private commentService: CommentService,
    private alertService: AlertService,
    private modal: NgbModal) { }

  ngOnInit() {
    this.getData();
    let param = this.route.snapshot.paramMap.get('alert');
    if (param == 'activationsucceeded') {
      this.alertService.addAlert('Activity successfully published. Thank you for your submission!', 'success');
    }
    if (param == 'commentactivationsucceeded') {
      this.alertService.addAlert('Comment successfully published. Thank you for your submission!', 'success');
    }
  }

  getData() {
    let id = +this.route.snapshot.paramMap.get('id');
    this.activityService.getActivity(id).subscribe(activity => this.activity = activity);
    this.commentService.getComments(id).subscribe(comments => this.comments = comments);
  }

  openCreateCommentModal() {
    let modalref = this.modal.open(PostcommentComponent);
    modalref.componentInstance.activityId = this.activity.id;
  }

  refresh() {
    this.getData();
    this.addAlert('refreshed');
  }

  addAlert(alert: string) {
    if (alert == 'activityactivationfailed') {
      this.alertService.addAlert('Activation failed! Try submitting your activity again.', 'danger');
    }
    if (alert == 'commentactivationfailed') {
      this.alertService.addAlert('Activation failed! Try submitting your comment again.', 'danger');
    }
    if (alert == 'refreshed') {
      this.alertService.addAlert('Data refreshed!', 'info')
    }
  }
}
