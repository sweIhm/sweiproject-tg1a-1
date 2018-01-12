import { Component, OnInit } from '@angular/core';
import {Activity} from "../../../model/activity";
import {NgbActiveModal} from "@ng-bootstrap/ng-bootstrap";
import {Router} from "@angular/router";

@Component({
  selector: 'app-searchactivity',
  templateUrl: './searchactivity.component.html',
  styleUrls: ['./searchactivity.component.css']
})
export class SearchactivityComponent implements OnInit {

  activities: Activity[] = [];
  searchterm: string = "";

  constructor(private activeModal: NgbActiveModal,
              private router: Router) { }

  ngOnInit() {
  }

  close() {
    this.activeModal.close();
  }

  navigate(activity: Activity) {
    this.activeModal.close();
    this.router.navigate(['/details/' + activity.id]);
  }

  get search(): Activity[] {
    if (this.searchterm == "") {
      return this.activities;
    }
    return this.activities.filter((activity) => {
      return activity.text.includes(this.searchterm) ||
        activity.author.includes(this.searchterm) ||
        activity.title.includes(this.searchterm);
    });
  }

}
