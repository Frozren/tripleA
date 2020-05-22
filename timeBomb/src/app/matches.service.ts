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
    this.http.get<Array<Match>>(this.apiUrl)
        .subscribe(matches => this.matches = matches);
  }

  public reloadTerminated(){
    this.http.get<Array<Match>>(`${this.apiUrl}/terminated`)
        .subscribe(matches => this.matches = matches);
  }

  public add(match){
    this.http.post<Match>(this.apiUrl, match)
        .subscribe();
  }

  public play(idPartie: number, id) {
    this.http.put(`${ this.apiUrl }/${ idPartie }/play`, id).subscribe(resp =>{});
  }

  public getEventSource(): EventSource {
    return new EventSource(`${this.apiUrl}/sse-stream`);
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
