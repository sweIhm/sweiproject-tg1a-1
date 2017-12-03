import {Component, OnInit} from '@angular/core';
import {Activity} from "../activity";
import {ActivatedRoute} from "@angular/router";
import {ActivityService} from "../activity.service";

@Component({
  selector: 'app-view',
  templateUrl: './view.component.html',
  styleUrls: ['./view.component.css']
})
export class ViewComponent implements OnInit {

  activity: Activity;

  constructor (
    private route: ActivatedRoute,
    private activityService: ActivityService) { }

  ngOnInit() {
    this.getActivity();
  }

  getActivity() {
    const id = +this.route.snapshot.paramMap.get('id');
    this.activityService.getActivity(id).subscribe(activity => this.activity = activity);
  }
}
