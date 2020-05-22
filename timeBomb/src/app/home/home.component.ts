import { Component, OnInit } from '@angular/core';
import { MatchService } from '../matches.service';
import { AppConfigService } from '../app-config.service';
import { ConnectComponent } from '../connect/connect.component';
import { Router } from '@angular/router';
import { Match } from '../match';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  isConnect: boolean = false;
  page: string = "";
  isCreating: boolean = false;
  match: Match = new Match();

  constructor(public srvMatch: MatchService, private router: Router) {
    this.srvMatch.reloadTerminated();
  }

  ngOnInit(): void {
  }

  join(page){
    this.srvMatch.page = page;
    this.router.navigate([ '/matches' ]);
  }

  create(){
    this.isCreating = true;
  }

  submit(){
    this.srvMatch.add(this.match);
    this.match = null;
    this.isCreating = false;
    this.router.navigate([ '/home' ]);
  }

  retour(){
    this.isCreating = false;
  }

  leave(){
    this.router.navigate([ '/connect' ]);
  }
}
