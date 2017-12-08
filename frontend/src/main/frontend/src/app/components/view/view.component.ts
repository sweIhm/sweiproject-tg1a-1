import {Component, OnInit} from '@angular/core';
import {Activity} from "../../model/activity";
import {ActivatedRoute} from "@angular/router";
import {ActivityService} from "../../services/activity.service";
import {AlertService} from "../../services/alert.service";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {PostcommentComponent} from "../postcomment/postcomment.component";
import {CommentService} from "../../services/comment.service";

@Component({
  selector: 'app-view',
  templateUrl: './view.component.html',
  styleUrls: ['./view.component.css']
})
export class ViewComponent implements OnInit {

  activity: Activity;
  comments: Comment[];

  constructor (
    private route: ActivatedRoute,
    private activityService: ActivityService,
    private commentService: CommentService,
    private alertService: AlertService,
    private modal: NgbModal) { }

  ngOnInit() {
    this.getData();
    const param = this.route.snapshot.paramMap.get('alert');
    if (param == 'activationsucceeded') {
      this.alertService.addAlert('Activity successfully published. Thank you for your submission!', 'success');
    }
  }

  getData() {
    const id = +this.route.snapshot.paramMap.get('id');
    this.activityService.getActivity(id).subscribe(activity => this.activity = activity);
    this.commentService.getComments(id).subscribe(comments => this.comments = comments);
  }

  openPostcommentModal() {
    const modalref = this.modal.open(PostcommentComponent);
    modalref.componentInstance.activityId = this.activity.id;
  }
}
