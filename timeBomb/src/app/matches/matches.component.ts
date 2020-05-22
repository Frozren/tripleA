import { Component, OnInit } from '@angular/core';
import { MatchService } from '../matches.service';
import { Match } from '../match';
import { Router } from '@angular/router';
import { HomeComponent } from '../home/home.component';

@Component({
  selector: 'app-matches',
  templateUrl: './matches.component.html',
  styleUrls: ['./matches.component.css']
})
export class MatchesComponent implements OnInit {

  match: Match = null;
  taille: number;

  constructor(public srvMatch: MatchService, private router: Router) {
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
    
    this.match = match;
  }

  join(){
    if (this.srvMatch.page == "join"){
      alert("rejoindre partie pour jouer : " + this.match.name);
    } else if (this.srvMatch.page == "look"){
      alert("rejoindre partie pour regarder : " + this.match.name);
    }
    
  }

  public matchesFiltered(){
    // return this.srvVisite.visites.filter(v => v.pseudo.toUpperCase().includes(this.filtre.toUpperCase()));
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
