import {Keyword} from "./keyword";

export class ActivityDto {

  constructor (
    public title: string,
    public text: string,
    public author: string,
    public email: string,
    public keywords: Keyword[]
  ) { };
}
