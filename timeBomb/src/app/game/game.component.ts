import { Component, OnInit } from '@angular/core';
import { User } from '../user';
import { Match } from '../match';
import { Card } from '../card';

@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.css']
})
export class GameComponent implements OnInit {
  deck1: Array<Card> = [];
  cards1: Array<Card> = [new Card(1,"BOMB",true), new Card(2,"DIFFUSE",true), 
  new Card(3,"BAIT",true), new Card(4,"BAIT",false), new Card(4,"BAIT",false), new Card(4,"BAIT",false)];
  cards2: Array<Card> = [new Card(5,"BAIT",false), new Card(6,"DIFFUSE",false), 
  new Card(7,"BAIT",true), new Card(8,"BAIT",true), new Card(4,"BAIT",false), new Card(4,"BAIT",false)];
  user1: User = new User(1,"Jordan","jordan","","SHERLOCK",1,this.cards1);
  user2: User = new User(1,"Jeremy","Jeremy","","MORIARTY",1,this.cards1);
  user3: User = new User(1,"adrien","adrien","","MORIARTY",1,this.cards2);
  public match: Match = new Match(1, "theGame", 2, "PLAYING", "", this.user1, this.user1, [this.user3,this.user1,this.user2,this.user3],this.deck1);

  public revealCard : Array<Card> =[new Card(6,"DIFFUSE",true),new Card(6,"DIFFUSE",true)];

  constructor() { }
  
  ngOnInit(): void {
  }

public playerFiltered() {
  return this.match.players.filter(p =>
    p != this.match.current);
}

public deck() {
  
}

public selectCard() {
  
}

}
