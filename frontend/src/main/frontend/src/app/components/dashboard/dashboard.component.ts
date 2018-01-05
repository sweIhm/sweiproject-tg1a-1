import { Component, OnInit } from '@angular/core';
import {ActivityService} from "../../services/activity.service";
import {Activity} from "../../model/activity";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {AlertService} from "../../services/alert.service";
import {ActivatedRoute, NavigationEnd, Router} from "@angular/router";
import {PostactivityComponent} from "./postactivity/postactivity.component";
import {Filter} from "../../model/filter";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  activities : Activity[] = [];
  filters: Filter[] = [];

  constructor(private service: ActivityService,
              private modal: NgbModal,
              private alertService: AlertService,
              private route: ActivatedRoute,
              private router: Router) {
    router.events.subscribe((event) => {
      if (event instanceof NavigationEnd) {
        this.getData();
      }
    });
  }

  ngOnInit() {
    let alert = this.route.snapshot.paramMap.get('alert');
    this.addAlert(alert);
  }

  getData() {
    let query = this.route.snapshot.queryParamMap.getAll('filter');
    this.service.getActivities(query).subscribe(activities => this.activities = activities);
    this.service.getKeywords().subscribe((keywords) => {
      this.filters = [];
      for (let keyword of keywords) {
        let filter = new Filter(keyword);
        filter.active = query.indexOf(keyword.content) >= 0;
        filter.selected = query.indexOf(keyword.content) >= 0;
        this.filters.push(filter);
      }
    });
  }

  refresh() {
    this.getData();
    this.alertService.addAlert('Data refreshed!', 'info')
  }

  openPostModal() {
    this.modal.open(PostactivityComponent);
  }

  addAlert(alert: string) {
    if (alert == 'activityactivationfailed') {
      this.alertService.addAlert('Activation failed! Try submitting your activity again.', 'danger');
    }
    if (alert == 'commentactivationfailed') {
      this.alertService.addAlert('Activation failed! Try submitting your comment again.', 'danger');
    }
    // TODO Dieser alert gehört eigentlich zu view
    if (alert == 'commentactivationsucceeded') {
      this.alertService.addAlert('Comment successfully published. Thank you for your submission!', 'success');
    }
  }

  navigate() {
    let params: string[] = [];
    for (let filter of this.filters) {
      if (filter.selected) {
        params.push(filter.keyword.content);
      }
    }
    if (params.length > 0) {
      this.router.navigate(['/dashboard'], {queryParams: {'filter': params }});
    }
    else {
      this.router.navigate(['/dashboard']);
    }
  }

  get hasActiveFilters() {
    return this.filters.some(filter => filter.active); // entspricht javas Stream.anyMatch
  }
}
