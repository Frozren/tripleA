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
  public connectionOK: boolean = false;

  constructor(private appConfig: AppConfigService, private http: HttpClient) {
    this.apiUrl = `${this.appConfig.url}/users`;
  }

  public add(user){
    return this.http.post<User>(`${this.apiUrl}/subscribe`, user);
  }

  public connexion(user){
    return this.http.post<User>(`${this.apiUrl}/login`, user);
  }
}
