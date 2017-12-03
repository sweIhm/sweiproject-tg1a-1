import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import {ActivityService} from '../activity.service';
import { Activity } from '../activity';

@Component({
  selector: 'app-activation',
  templateUrl: './activation.component.html',
  styleUrls: ['./activation.component.css']
})
export class ActivationComponent implements OnInit {

  id: number;
  published: boolean;
  activity: Activity;

  constructor(private route: ActivatedRoute,
                private service: ActivityService) { }

  ngOnInit() {
    this.id = +this.route.snapshot.params['id'];
    this.published = this.route.snapshot.queryParams['success'] == 'true' ? true : false;
    if (this.published) {
      this.service.getActivity(this.id).subscribe(response => {
        this.activity = response.body;
      });
    }
  }

}