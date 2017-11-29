import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ActivityDto} from "./activity-dto";
import {Observable} from "rxjs/Observable";
import {Activity} from "./activity";

@Injectable()
export class ActivityService {

  url : string = 'http://localhost:8080/activity';

  constructor(private http:HttpClient) { }

  addActivity(activity : ActivityDto) : Observable<ActivityDto> {
    return this.http.post(this.url, activity);
  }

  getActivities() : Observable<Activity[]> {
    return this.http.get(this.url);
  }
}
