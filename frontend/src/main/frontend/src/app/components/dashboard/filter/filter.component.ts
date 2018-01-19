import {Component, Input, OnInit} from '@angular/core';
import {Filter} from "../../../model/filter";

@Component({
  selector: 'app-filter',
  templateUrl: './filter.component.html',
  styleUrls: ['./filter.component.css']
})
export class FilterComponent implements OnInit {

  @Input() filters: Filter[] = [];

  constructor() { }

  ngOnInit() {
  }

}
