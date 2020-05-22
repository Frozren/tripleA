import { Component, OnInit } from '@angular/core';
import { User } from '../user';
import { Match } from '../match';
import { Card } from '../card';
import { MatchService } from '../matches.service';
import { UserService } from '../user.service';

@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.css']
})
export class GameComponent implements OnInit {
  public card1 = new Card(1,"BAIT",false);
  public deck1 = [this.card1];
  public user1 = new User(1,"Adrien","","","",1,)
  public users1 = [this.user1];
  public match = new Match(1,"nc",1,"WAITING","",this.user1,this.user1,this.users1,this.deck1);
  public playerName: string;

  constructor(private matchService: MatchService, private userService: UserService) {
    matchService.getServerSentEvent().subscribe(match => this.match = match);
    this.playerName = this.userService.user.name;
  }
  
  ngOnInit(): void {
    this.matchService
      .getServerSentEvent()
      .subscribe(match => {
        console.log(match);
      });
  }

  public start() {
    if (this.match.state =="WAITING" || this.match.state =="READY") {return false;}
    else {return true;}
  }

  public btnStart() {
    if (this.match.state =="READY" && this.player()==this.match.owner) {return true;}
    else {return false;}
  }

  public lancer() {
    if(this.player()==this.match.owner) {
    this.matchService.launch(this.match);}
    
  }

public playerFiltered() {
  return this.match.players.filter(p =>
    p.username != this.playerName);
}

public player() {
  let player = this.match.players.filter(p =>
    p.username == this.playerName);
    return player[0];
}

public revealCards() {
  return this.match.deck.filter(d => d.type == "DIFFUSE" && d.revealed);
}

public selectCard(id: number) {
  //alert(id);
  this.matchService.play(this.match, id);
}

public endGame() {
  let end: Boolean=false;
  let nbBomb=0;
  let nbDiffuse=0;
  let nbReveal=0;
  for (let d of this.match.deck) {
    if(d.type == "BOMB" && d.revealed) {nbBomb++}
  }
  for (let d of this.match.deck) {
    if(d.type == "DIFFUSE" && d.revealed) {nbDiffuse++}
  }
  for (let d of this.match.deck) {
    if(d.revealed) {nbReveal++}
  }

  if (nbBomb>0) {end=true}
  if (nbDiffuse==this.match.size) {end=true}
  if (nbReveal==this.match.size*4) {end=true}

  return end;
}

}
