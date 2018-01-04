import {Keyword} from "./keyword";

export class Filter {

  selected: boolean = false;
  active: boolean = false;

  constructor(public keyword: Keyword) {};
}
