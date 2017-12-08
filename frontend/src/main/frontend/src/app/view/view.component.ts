import {Component, Input, OnInit} from '@angular/core';
import {Activity} from "../activity";
import {NgbActiveModal} from "@ng-bootstrap/ng-bootstrap";

@Component({
  selector: 'app-view',
  templateUrl: './view.component.html',
  styleUrls: ['./view.component.css']
})
export class ViewComponent implements OnInit {

  @Input() activity: Activity;

  id: number;

  constructor(public activeModal:NgbActiveModal) { }

  ngOnInit() {
  }

}
