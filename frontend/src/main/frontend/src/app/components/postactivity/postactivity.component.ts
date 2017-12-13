import { Component, OnInit } from '@angular/core';
import {ActivityDto} from "../../model/activity-dto";
import {ActivityService} from "../../services/activity.service";
import {NgbActiveModal} from "@ng-bootstrap/ng-bootstrap";
import {AlertService} from "../../services/alert.service";
import {Keyword} from "../../model/keyword";

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
    this.alertService.addAlert('Thank you very much! Check your mails so you can post your activity!', 'info')
  }

  onShowMore(event) : void {
    event.preventDefault();
    this.limit = this.keywords.length;
  }

  onTagChoice(event) {
    event.preventDefault();
    let target = event.currentTarget;
    if (target.classList[1] === 'badge-primary') {
      target.classList.add('badge-secondary');
      target.classList.remove('badge-primary');
      for (let i: number = 0; i < this.toBePosted.keywords.length; i++) {
        if (this.toBePosted.keywords[i].content === target.text) {
          this.toBePosted.keywords.splice(i,1);
          i -= 1;
        }
      }
    } else if (target.classList[1] === 'badge-secondary') {
      target.classList.add('badge-primary');
      target.classList.remove('badge-secondary');
      this.toBePosted.keywords.push(new Keyword(target.innerText));
    }
  }

}
