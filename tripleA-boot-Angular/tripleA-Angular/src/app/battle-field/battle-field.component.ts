import { Component, OnInit } from '@angular/core';
import { Card } from '../model/card';

@Component({
  selector: 'app-battle-field',
  templateUrl: './battle-field.component.html',
  styleUrls: ['./battle-field.component.css']
})
export class BattleFieldComponent implements OnInit {

  card1: Card = new Card(1,100,20,25);
  card2: Card = new Card(1,100,20,25);
  card3: Card = new Card(1,100,20,25);
  card4: Card = new Card(1,100,20,25);
  card5: Card = new Card(1,100,20,25);
  card6: Card = new Card(1,100,20,25);
  deckH = [this.card1,this.card2,this.card3];
  deckAI = [this.card4,this.card5,this.card6];
  phase=1;
  musicUrl="";


  cursorch = "";
  cursorai = "";
  hpb1 = 100;
  styleHp1;
  hpb2 = 100;
  message = "test";
  cursor = "";
  
  musicBattle = "";
  
  c1=1;c2=2;c3=3;c4=4;c5=5;c6=6;
  idCard1 = 1; idCard2 = 2; idCard3 = 3;
  idCard4 = 4; idCard5 = 5; idCard6 = 6;
  name=["","LEGOLAS","ARAGORN","GIMLI"];
  class=["","Le Codeur","Le Hacker","Le Debugeur"];
  img=["","assets/img/battlefield/card/cardAP.png",
  "assets/img/battlefield/card/cardAB.png",
  "assets/img/battlefield/card/cardAV.png"];
  namec1 = this.name[this.c1];
  classc1 = this.class[this.c1];
  img1 = this.img[this.c1];
  namec2 = this.name[this.c2];
  classc2 = this.class[this.c2];
  img2 = this.img[this.c2];;
  namec3 = this.name[this.c3];
  classc3 = this.class[this.c3];
  img3 = this.img[this.c3];
  namec4 = "";
  classc4 = "";
  img4 = "assets/img/battlefield/card/cardTG.png";
  namec5 = "";
  classc5 = "";
  img5 = "assets/img/battlefield/card/cardJR.png";
  namec6 = "";
  classc6 = "";
  img6 = "";
  imgField = "";
  
  dmg1 = 0; dmg2 = 0; dmg3 = 0;
  dmg4 = 0; dmg5 = 0; dmg6 = 0;
  dmgBoss = 0;




  constructor() { }

  ngOnInit(): void {
    if (this.phase == 1) {
      this.namec6="SAROUMANE";
      this.classc6="Le Magicien";
      this.img6="assets/img/battlefield/card/cardJA.png";
    } else {
      this.namec6="SAURON";
      this.classc6="L'Omniscient";
      this.img6="assets/img/battlefield/card/cardJP.png";
    }
    if (this.phase == 3) {
      this.imgField="assets/img/battlefield/field/bg2.jpg";
      this.musicBattle="assets/sound/music6.mp3";
    }
    else {
      this.musicBattle=this.musicUrl;
    }
  }


}
