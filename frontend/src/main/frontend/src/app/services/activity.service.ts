import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ActivityDto} from "../model/activity-dto";
import {Observable} from "rxjs/Observable";

@Injectable()
export class ActivityService {

  url : string = '/api/activity';

  constructor(private http:HttpClient) { }

  addActivity(activity : ActivityDto) : Observable<any> {
    return this.http.post(this.url, activity);
  }

  getActivities() : Observable<any> {
    return this.http.get(this.url);
  }

  getActivity(id: number) : Observable<any> {
    return this.http.get(this.url + '/' + id);
  }

  getKeywords() : Observable<any> {
    return this.http.get(this.url + '/keywords');
  }
}
