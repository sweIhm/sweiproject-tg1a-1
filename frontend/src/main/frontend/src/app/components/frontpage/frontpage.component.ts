import { Component, OnInit } from '@angular/core';
import {ActivityService} from "../../services/activity.service";
import {Activity} from "../../model/activity";
import {Keyword} from "../../model/keyword";

@Component({
  selector: 'app-frontpage',
  templateUrl: './frontpage.component.html',
  styleUrls: ['./frontpage.component.css']
})
export class FrontpageComponent implements OnInit {

  trendingKeywords: Keyword[];
  trendingPosts: Activity[];
  trendingHM: Activity[];
  trendingCALPOLY: Activity[];

  constructor(private service: ActivityService) { }

  ngOnInit() {
    this.service.getTrending().subscribe(x => this.trendingPosts = x);
    this.service.getTrendingHM().subscribe(x => this.trendingHM = x);
    this.service.getTrendingCALPOLY().subscribe(x => this.trendingCALPOLY = x);
    this.service.getTrendingKeywords().subscribe(x => this.trendingKeywords = x);
  }

}
