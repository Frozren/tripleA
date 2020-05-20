import { Injectable } from '@angular/core';
import { Match } from './match';
import { AppConfigService } from './app-config.service';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class MatchService {
  private apiUrl: string = "";
  public matches : Array<Match> = null;

  constructor(private appConfig: AppConfigService, private http: HttpClient) {
    this.apiUrl = `${this.appConfig.url}/matches`;
  }

  public reload(){
    this.http.get<Array<Match>>(this.apiUrl)
        .subscribe(matches => this.matches = matches);
  }

  public add(match){
    this.http.post<Match>(this.apiUrl, match)
        .subscribe(match => this.matches.push(match));
  }


}
