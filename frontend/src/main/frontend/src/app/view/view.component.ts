import {Component, Input, OnInit} from '@angular/core';
import {Activity} from "../activity";

@Component({
  selector: 'app-view',
  templateUrl: './view.component.html',
  styleUrls: ['./view.component.css']
})
export class ViewComponent implements OnInit {

  @Input() activity: Activity;

  constructor() { }

  ngOnInit() {
  }

}
