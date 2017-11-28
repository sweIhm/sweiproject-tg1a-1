import { Component, OnInit } from '@angular/core';
import { Activity } from '../activity';

@Component({
  selector: 'app-activities',
  templateUrl: './activities.component.html',
  styleUrls: ['./activities.component.css']
})
export class ActivitiesComponent implements OnInit {

  activity: Activity = {
    id: 1,
    author: 'Hans Wurst',
    title: 'Herpes',
    text: 'herpes fuer alle'
  };

  constructor() { }

  ngOnInit() {
  }

}
