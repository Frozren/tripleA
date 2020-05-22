import { Component, OnInit } from '@angular/core';
import { MatchService } from '../matches.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  isConnect: boolean = false;
  page: string = "menu";

  match = {
    name: "OUIOUI",
    size: 5,
    state: "WAITING",
    winner: null,
    owner: {
      id: 154,
      name: "BEN"
    }
  }

  constructor(public srvMatch: MatchService) {
    this.srvMatch.reloadTerminated();
  }

  ngOnInit(): void {
  }

  test(){
    alert("test");
    this.srvMatch.add(this.match);
  }

  leave(){
    
  }
}
