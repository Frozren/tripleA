import { Injectable } from '@angular/core';
import { User } from './user';
import { AppConfigService } from './app-config.service';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private apiUrl: string = "";
  public user: User = null;

  constructor(private appConfig: AppConfigService, private http: HttpClient) {
    this.apiUrl = `${this.appConfig.url}/users`;
  }

  public add(user){
    this.http.post<User>(`${this.apiUrl}/subscribe`, user)
        .subscribe(user => this.user = user);
  }

  public connexion(user){
    this.http.post<User>(`${this.apiUrl}/login`, user)
        .subscribe(user => console.log("post login: " + user));
  }
}
