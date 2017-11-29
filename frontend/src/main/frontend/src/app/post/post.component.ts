import { Component, OnInit } from '@angular/core';
import {ActivityDto} from "../activity-dto";
import {ActivityService} from "../activity.service";

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit {

  submitted: boolean = false;
  toBePosted: ActivityDto = {
    title: '',
    text: '',
    email: '',
    author: ''
  };

  constructor(private service: ActivityService) { }

  ngOnInit() {
  }

  onSubmit() {
    this.service.addActivity(this.toBePosted).subscribe();
    this.toBePosted = {
      title: '',
      text: '',
      email: '',
      author: ''
    }
    this.submitted = true;
  }

  dismissAlert() {
    this.submitted = false;
  }

  get diagnostic() { return JSON.stringify(this.toBePosted); }
}
