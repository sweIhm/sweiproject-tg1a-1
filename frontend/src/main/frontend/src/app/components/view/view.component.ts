import {Component, OnInit} from '@angular/core';
import {Activity} from "../../model/activity";
import {ActivatedRoute} from "@angular/router";
import {ActivityService} from "../../services/activity.service";
import {AlertService} from "../../services/alert.service";

@Component({
  selector: 'app-view',
  templateUrl: './view.component.html',
  styleUrls: ['./view.component.css']
})
export class ViewComponent implements OnInit {

  activity: Activity;

  constructor (
    private route: ActivatedRoute,
    private activityService: ActivityService,
    private alertService: AlertService) { }

  ngOnInit() {
    this.getActivity();
    const param = this.route.snapshot.paramMap.get('alert');
    if (param == 'activationsucceeded') {
      this.alertService.addAlert('Activity successfully published. Thank you for your submission!', 'success');
    }
  }

  getActivity() {
    const id = +this.route.snapshot.paramMap.get('id');
    this.activityService.getActivity(id).subscribe(activity => this.activity = activity);
  }
}
