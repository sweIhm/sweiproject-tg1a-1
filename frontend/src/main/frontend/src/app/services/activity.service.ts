import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ActivityDto} from "../model/activity-dto";
import {Observable} from "rxjs/Observable";
import {Activity} from "../model/activity";

@Injectable()
export class ActivityService {

  url : string = '/activity';

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
}
