import { Component, OnInit } from '@angular/core';
import {ActivityDto} from "../activity-dto";
import {ActivityService} from "../activity.service";
import {NgbActiveModal} from "@ng-bootstrap/ng-bootstrap";
import {AlertService} from "../alert.service";

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit {

  submitted: boolean = false;
  toBePosted: ActivityDto = new ActivityDto('', '', '', '');

  constructor(private service: ActivityService,
              private activeModal: NgbActiveModal,
              private alertService: AlertService) { }

  ngOnInit() {
  }

  onSubmit() {
    this.service.addActivity(this.toBePosted).subscribe();
    this.toBePosted = new ActivityDto('', '', '', '');
    this.submitted = true;
    this.activeModal.close();
    this.alertService.addAlert('Thank you very much! Check your mails so you can post your activity!', 'info')
  }

}
