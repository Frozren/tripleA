import { Injectable } from '@angular/core';
import { HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AppConfigService {
  public url: string = "http://176.143.99.66:8080/api";
  public httpOptions: Object = null;

  constructor() {}

  setLogin(user){
    let myHeaders: HttpHeaders = new HttpHeaders();
    myHeaders = myHeaders.append('Authorization', 'Basic ' + btoa(user.username + ":" + user.password));
    this.httpOptions = {headers: myHeaders};
  }
}
