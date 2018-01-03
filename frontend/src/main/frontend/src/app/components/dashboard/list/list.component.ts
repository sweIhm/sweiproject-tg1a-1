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

}
