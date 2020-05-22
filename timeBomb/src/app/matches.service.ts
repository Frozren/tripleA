import { Injectable, NgZone } from '@angular/core';
import { Match } from './match';
import { AppConfigService } from './app-config.service';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MatchService {
  private apiUrl: string = "";
  public matches : Array<Match> = null;
  public page: string = "";

  constructor(private appConfig: AppConfigService, private http: HttpClient, private zone: NgZone) {
    this.apiUrl = `${this.appConfig.url}/matches`;
    
  }

  public reload(){
    this.http.get<Array<Match>>(this.apiUrl, this.appConfig.httpOptions)
        .subscribe(matches => this.matches = matches);
  }

  public reloadTerminated(){
    this.http.get<Array<Match>>(`${this.apiUrl}/terminated`, this.appConfig.httpOptions)
        .subscribe(matches => this.matches = matches);
  }

  public add(match){
    this.http.post<Match>(this.apiUrl, match, this.appConfig.httpOptions)
        .subscribe();
  }

  public join(match){
    this.http.put(`${this.apiUrl}/${match.id}`, match, this.appConfig.httpOptions)
        .subscribe();
  }

  public delete(match){
    this.http.delete(`${this.apiUrl}/${match.id}`, this.appConfig.httpOptions)
        .subscribe(resp => {
          if(resp){
            let index = this.matches.indexOf(match);
            this.matches.splice(index, 1);
          }
        });
  }

  public launch(match) {
    this.http.put(`${ this.apiUrl }/${ match.id }/start`,match, this.appConfig.httpOptions).subscribe(resp =>{});
  }

  public play(match, id) {
    this.http.put(`${ this.apiUrl }/${ match.id }/play`, id, this.appConfig.httpOptions).subscribe(resp =>{});
  }

  public getEventSource(): EventSource {
    return new EventSource(`${this.apiUrl}/sse-stream`, this.appConfig.httpOptions);
  }

  public getServerSentEvent() {
    return Observable.create(observer => {
      const eventSource = this.getEventSource();

      eventSource.onmessage = event => {
        this.zone.run(() => {
          observer.next(event);
        });
      };
      eventSource.onerror = error => {
        this.zone.run(() => {
          observer.error(error);
        });
      };
    });
  }
}
