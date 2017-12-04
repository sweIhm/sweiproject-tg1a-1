import { Injectable } from '@angular/core';

@Injectable()
export class AlertService {

  alerts: Alert[] = [];

  constructor() { }

  addAlert(msg: string, type: string) {
    this.alerts.push(new Alert(msg, type));
  }

  removeAlert(index: number) {
    this.alerts.splice(index, 1);
  }

}

export class Alert {
  constructor(public msg: string, public type: string) {}
}
