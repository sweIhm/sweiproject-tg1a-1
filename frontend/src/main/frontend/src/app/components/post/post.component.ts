import { Component, OnInit } from '@angular/core';
import {ActivityDto} from "../../model/activity-dto";
import {ActivityService} from "../../services/activity.service";
import {NgbActiveModal} from "@ng-bootstrap/ng-bootstrap";
import {AlertService} from "../../services/alert.service";
import {Tag} from "../../model/tag";

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit {

  toBePosted: ActivityDto = new ActivityDto('', '', '', '');

  tags: Tag[] = [];
  limit: number = 2;

  constructor(private service: ActivityService,
              private activeModal: NgbActiveModal,
              private alertService: AlertService) { }

  ngOnInit() {
    this.service.getTags().subscribe(tags => this.tags = tags);
  }

  onSubmit() {
    this.service.addActivity(this.toBePosted).subscribe();
    this.activeModal.close();
    this.alertService.addAlert('Thank you very much! Check your mails so you can post your activity!', 'info')
  }

  onShowMore(event) {
    event.preventDefault();
    this.limit = this.tags.length;
  }

}
