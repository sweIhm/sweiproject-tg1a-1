import {Component, Input, OnInit} from '@angular/core';
import {Activity} from "../../../model/activity";

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListComponent implements OnInit {

  @Input() activities : Activity[] = [];

  constructor() { }

  ngOnInit() {
  }

  shortText(activity: Activity): string {
    if (activity.text.length >= 100) {
      return activity.text.slice(0, 98) + '\u2026'; // ellipse
    }
    return activity.text;
  }
}
