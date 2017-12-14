import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ActivityDto} from "../model/activity-dto";
import {Observable} from "rxjs/Observable";
import {Activity} from "../model/activity";
import {Keyword} from "../model/keyword";

@Injectable()
export class ActivityService {

  url : string = '/api/activity';

  constructor(private http:HttpClient) { }

  addActivity(activity : ActivityDto) : Observable<ActivityDto> {
    return this.http.post(this.url, activity);
  }

  getActivities() : Observable<Activity[]> {
    return this.http.get(this.url);
  }

  getActivity(id: number) : Observable<Activity> {
    return this.http.get(this.url + '/' + id);
  }

  getKeywords() : Observable<Keyword[]> {
    return this.http.get(this.url + '/keywords');
  }
}
