import { Component, OnInit } from '@angular/core';
import { MatchService } from '../matches.service';
import { Match } from '../match';
import { Router } from '@angular/router';
import { HomeComponent } from '../home/home.component';
import { UserService } from '../user.service';

@Component({
  selector: 'app-matches',
  templateUrl: './matches.component.html',
  styleUrls: ['./matches.component.css']
})
export class MatchesComponent implements OnInit {

  match: Match = null;
  taille: number;
  suppr: boolean = false;

  constructor(public srvMatch: MatchService, public srvUser: UserService, private router: Router) {
    this.srvMatch.reload();
  }

  ngOnInit(): void {
  }

  charger(match){
    let lignes = document.getElementsByTagName('tr');

    for (let i=0; i<lignes.length; i++){
      lignes[i].style.backgroundColor = '';
    }

    document.getElementById("match" + match.id).style.backgroundColor = 'rgba(117, 35, 35, 0.5)';

    if (match.owner.id == this.srvUser.user.id){
      this.suppr = true;
    } else {
      this.suppr = false;
    }
    
    this.match = match;
  }

  join(){
    this.srvMatch.match = this.match;
    if (this.srvMatch.page == "join"){
      this.router.navigate([ '/game' ]);
    } else if (this.srvMatch.page == "look"){
      this.router.navigate([ '/game' ]);
    }
  }

  delete(){
    this.srvMatch.delete(this.match);
    this.router.navigate([ '/home' ]);
  }

  public matchesFiltered(){
    let tab: Array<Match>;
    if (this.srvMatch.page == "join"){
      tab = this.srvMatch.matches.filter(m => m.state == "WAITING");
    } else if (this.srvMatch.page == "look"){
      tab = this.srvMatch.matches.filter(m => m.state == "PLAYING");
    }
    this.taille = tab.length;
    return tab;
  }
}
