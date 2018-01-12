import { Component, OnInit } from '@angular/core';
import {ActivityDto} from "../../../model/activity-dto";
import {ActivityService} from "../../../services/activity.service";
import {NgbActiveModal} from "@ng-bootstrap/ng-bootstrap";
import {AlertService} from "../../../services/alert.service";
import {Keyword} from "../../../model/keyword";

@Component({
  selector: 'app-post',
  templateUrl: './postactivity.component.html',
  styleUrls: ['./postactivity.component.css']
})
export class PostactivityComponent implements OnInit {

  toBePosted: ActivityDto = new ActivityDto('', '', '', '', []);

  keywords: Keyword[] = [];
  limit: number = 2;

  constructor(private service: ActivityService,
              private activeModal: NgbActiveModal,
              private alertService: AlertService) { }

  ngOnInit() {
    this.service.getKeywords().subscribe(keywords => this.keywords = keywords);
  }

  onSubmit() {
    this.service.addActivity(this.toBePosted).subscribe();
    this.activeModal.close();
    this.alertService.addAlert('Thank you very much! Check your mails so you can post your activity!', 'success')
  }

  toggleKeyword(keyword: Keyword) {
    let index = this.toBePosted.keywords.indexOf(keyword);
    if (index > -1) {
      this.toBePosted.keywords.splice(index, 1);
    }
    else {
      this.toBePosted.keywords.push(keyword);
    }
  }

  showMore() {
    this.limit = this.keywords.length;
  }

}
