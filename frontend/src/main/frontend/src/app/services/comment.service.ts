import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {CommentDto} from "../model/comment-dto";
import {Observable} from "rxjs/Observable";

@Injectable()
export class CommentService {

  constructor(private http: HttpClient) { }

  addComment(activityId: number, comment : CommentDto) : Observable<any> {
    let url = '/api/activity/' + activityId + '/comment';
    return this.http.post(url, comment);
  }

  getComments(activityId: number) : Observable<any> {
    let url = '/api/activity/' + activityId + '/comment';
    return this.http.get(url);
  }

}
