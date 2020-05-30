import { Player } from './player';
import { Card } from './card';

export class Human implements Player {
    constructor(
        public id?: number,
        public idOpponent?: number,
        public name?: string,
        public typePlayer?: Boolean,
        public card1?: Card,
        public card2?: Card,
        public card3?: Card,
        public dmgDealt?: number,
        public dmgTaken?: number,
        public phase?: number
    ) {
        this.typePlayer = true;
    }
}
