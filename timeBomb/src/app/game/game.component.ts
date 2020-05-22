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
  //public playerName: string = "jordan";
  public playerName: string;

  deck1: Array<Card> = [new Card(4,"BAIT",false), new Card(4,"BAIT",false), new Card(6,"DIFFUSE",true),new Card(6,"DIFFUSE",true)];
  cards1: Array<Card> = [new Card(1,"BOMB",false), new Card(2,"DIFFUSE",true), 
  new Card(3,"BAIT",true), new Card(4,"BAIT",false), new Card(4,"BAIT",false), new Card(4,"BAIT",false)];
  cards2: Array<Card> = [new Card(5,"BAIT",false), new Card(6,"DIFFUSE",false), 
  new Card(7,"BAIT",true), new Card(8,"BAIT",true), new Card(4,"BAIT",false), new Card(4,"BAIT",false)];
  cards3: Array<Card> = [new Card(5,"BAIT",false), new Card(6,"DIFFUSE",false), 
  new Card(7,"BAIT",true), new Card(8,"DIFFUSE",true), new Card(4,"BAIT",false), new Card(4,"BAIT",true)];
  cards4: Array<Card> = [new Card(5,"BAIT",false), new Card(6,"DIFFUSE",false), 
  new Card(7,"BAIT",true), new Card(8,"DIFFUSE",true), new Card(4,"DIFFUSE",true), new Card(4,"BAIT",false)];
  user1: User = new User(1,"Jordan","jordan","","SHERLOCK",1,this.cards1);
  user2: User = new User(1,"Jeremy","jeremy","","MORIARTY",1,this.cards3);
  user3: User = new User(1,"Adrien","adrien","","SHERLOCK",1,this.cards2);
  user4: User = new User(1,"Julien","julien","","MORIARTY",1,this.cards4);
  public match: Match = new Match(1, "theGame", 4, "READY", "", this.user1, this.user1, [this.user1,this.user2,this.user3,this.user4],this.deck1);

  public revealCard : Array<Card> =[new Card(6,"DIFFUSE",true),new Card(6,"DIFFUSE",true)];

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
