import {Component, Input, OnInit} from '@angular/core';
import {Activity} from "../../../model/activity";

@Component({
  selector: 'app-activity',
  templateUrl: './activity.component.html',
  styleUrls: ['./activity.component.css']
})
export class ActivityComponent implements OnInit {

  @Input() activity: Activity;

  constructor() { }

  ngOnInit() {
  }

}
