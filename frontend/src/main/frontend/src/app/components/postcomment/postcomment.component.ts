import {Component, Input, OnInit} from '@angular/core';
import {CommentDto} from "../../model/comment-dto";
import {CommentService} from "../../services/comment.service";
import {NgbActiveModal} from "@ng-bootstrap/ng-bootstrap";
import {AlertService} from "../../services/alert.service";

@Component({
  selector: 'app-postcomment',
  templateUrl: './postcomment.component.html',
  styleUrls: ['./postcomment.component.css']
})
export class PostcommentComponent implements OnInit {

  activityId: number;
  toBePosted: CommentDto = new CommentDto('', '', '');

  constructor(private commentService: CommentService,
              private activeModal: NgbActiveModal,
              private alertService: AlertService) { }

  ngOnInit() {
  }

  onSubmit() {
    this.commentService.addComment(this.activityId, this.toBePosted).subscribe();
    this.activeModal.close();
    this.alertService.addAlert('Thank you very much! Check your mails so you can post your comment!', 'info')
  }

}
